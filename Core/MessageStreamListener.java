package Core;

import Event.LucciolaEvent;
import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

public class MessageStreamListener implements UserStreamListener {

	private LucciolaEvent lucciolaEvent;
	private Message message;
	private Twitter twitter;
	
	public MessageStreamListener(LucciolaEvent arg0, Message arg1, Twitter arg2) {
		this.lucciolaEvent = arg0;
		this.message = arg1;
		this.twitter = arg2;
	}
	
	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatus(Status arg0) {
		// TODO Auto-generated method stub
		try {
			if(arg0.getInReplyToScreenName() == twitter.getScreenName()) {
				message.addMenthons(arg0);
				lucciolaEvent.onEvent("onMenthon");
			} else lucciolaEvent.onEvent("onStatus");
			message.addTweets(arg0);
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
		}
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub
		lucciolaEvent.onException(arg0.getMessage());
	}

	@Override
	public void onBlock(User arg0, User arg1) {
		// TODO Auto-generated method stub
		try {
			if(arg1.getScreenName() == twitter.getScreenName())lucciolaEvent.onEvent("onBlock:" + arg0.getScreenName());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
		}
	}

	@Override
	public void onDeletionNotice(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDirectMessage(DirectMessage arg0) {
		// TODO Auto-generated method stub
		message.addDMs(arg0);
		lucciolaEvent.onEvent("onDirectMessage");
	}

	@Override
	public void onFavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFollow(User arg0, User arg1) {
		// TODO Auto-generated method stub
		try {
			if(arg1.getScreenName() == twitter.getScreenName())lucciolaEvent.onEvent("onFollow:" + arg0.getScreenName());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
		}
	}

	@Override
	public void onFriendList(long[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnblock(User arg0, User arg1) {
		// TODO Auto-generated method stub
		try {
			if(arg1.getScreenName() == twitter.getScreenName())lucciolaEvent.onEvent("onUnBlock:" + arg0.getScreenName());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
		}
	}

	@Override
	public void onUnfavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnfollow(User arg0, User arg1) {
		// TODO Auto-generated method stub
		try {
			if(arg1.getScreenName() == twitter.getScreenName())lucciolaEvent.onEvent("onUnFollow:" + arg0.getScreenName());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
		}
	}

	@Override
	public void onUserListCreation(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListDeletion(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserListUpdate(User arg0, UserList arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserProfileUpdate(User arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavoritedRetweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQuotedTweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRetweetedRetweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserDeletion(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUserSuspension(long arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
