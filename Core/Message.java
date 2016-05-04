package Core;

import java.util.ArrayList;
import java.util.List;

import twitter4j.*;

public class Message {

	private List<DirectMessage> dms;
	private List<Status> tweets;
	private List<Status> menthons;
	private int maxSize;
	
	public Message() {
		tweets = new ArrayList<Status>(0);
		this.dms = new ArrayList<DirectMessage>(0);
	}
	
	protected void addTweets(Status arg0) {
		if(tweets.size() + 1 < this.maxSize) {
			tweets.add(0, arg0);
		} else {
			tweets.remove(tweets.size() - 1);
			tweets.add(0, arg0);
		}
	}
	
	protected void addDMs(DirectMessage arg0) {
		this.dms.add(0, arg0);
	}
	
	protected void addMenthons(Status arg0) {
		this.menthons.add(0, arg0);
	}
	
	public List<Status> getTweets() {
		return tweets;
	}
	
	public List<DirectMessage> getDMs() {
		return dms;
	}
	
	public List<Status> getMenthons() {
		return menthons;
	}
	
}
