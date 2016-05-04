package OAuth;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class UserLoginer {
	
	private Twitter twitter;
	private String accessToken;
	private String accessTokenSecret;
	
	private String ConsumerKey = "JaZzgfvrdqVXN3XhcY8Q";
	private String ConsumerSecret = "y0NsmBwGLtjsVq0DHzai064TrgJBhXQsqfb36h6kY";
	
	public UserLoginer() {
	}
	
	public UserLoginer(String arg0, String arg1) {
		this.accessToken = arg0;
		this.accessTokenSecret = arg1;
	}
	
	public void setAccessToken(String arg0, String arg1) {
		this.accessToken = arg0;
		this.accessTokenSecret = arg1;
	}
	
	private ConfigurationBuilder createBuilder(String Access_token, String Access_token_secret) {
		ConfigurationBuilder conf = new ConfigurationBuilder();
		conf.setOAuthConsumerKey(ConsumerKey);
		conf.setOAuthConsumerSecret(ConsumerSecret);
		conf.setOAuthAccessToken(Access_token);
		conf.setOAuthAccessTokenSecret(Access_token_secret);
		conf.setUserStreamBaseURL("https://userstream.twitter.com/2/");
		return conf;
	}
	
	public Twitter login() {
		twitter = new TwitterFactory(createBuilder(accessToken, accessTokenSecret).build()).getInstance();
		return twitter;
	}
	
	public Twitter getTwitter() {
		return this.twitter;
	}
	
}
