package Core;

import java.util.concurrent.ExecutorService;

import Event.*;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PostTweet implements LucciolaListener {
	private Twitter twitter;
	private LucciolaEvent lucciolaEvent;
	private ExecutorService pool;
	
	public PostTweet(Twitter arg0, LucciolaEvent arg1, ExecutorService arg2) {
		twitter = arg0;
		lucciolaEvent = arg1;
		pool = arg2;
	}
	
	@Override
	public void onEvent(String arg0) {
		// TODO Auto-generated method stub
		if(arg0.split(":")[0].equals("tweet")) {
			pool.execute(() -> {try {
				twitter.updateStatus(arg0.split(":")[1]);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				lucciolaEvent.onException(e.getMessage());
			}});
		}
	}

	@Override
	public void onException(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
