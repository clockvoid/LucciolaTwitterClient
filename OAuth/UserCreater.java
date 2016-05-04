package OAuth;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class UserCreater {
	
	private String ConsumerKey = "JaZzgfvrdqVXN3XhcY8Q";
	private String ConsumerSecret = "y0NsmBwGLtjsVq0DHzai064TrgJBhXQsqfb36h6kY";
	private Twitter twitter;
	private BufferedWriter writer;
	
	public UserCreater(BufferedWriter arg) {
		this.writer = arg;
	}
	
	public Twitter createUser() {
		try {
			twitter = TwitterFactory.getSingleton();
			twitter.setOAuthConsumer(ConsumerKey, ConsumerSecret);
			RequestToken requesttoken = twitter.getOAuthRequestToken();
			String pin = getPin(requesttoken);
			AccessToken accesstoken = twitter.getOAuthAccessToken(requesttoken, pin);
			storeAccessToken(accesstoken);
			twitter = new TwitterFactory(createBuilder(accesstoken.getToken(), accesstoken.getTokenSecret()).build()).getInstance();
		} catch(TwitterException e) {
			if(e.getStatusCode() == 401) {
				System.out.println("Unable to Access Token.");
			}
		} catch(URISyntaxException e) {
			System.out.println("Unable to URL");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return twitter;
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
	
	private String getPin(RequestToken requesttoken) throws IOException, URISyntaxException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Desktop.getDesktop().browse(new URI(requesttoken.getAuthorizationURL()));
		System.out.println("Enter PIN:");
		return reader.readLine();
	}
	
	private void storeAccessToken(AccessToken accesstoken) throws IOException, TwitterException {
		this.writer.write(accesstoken.getToken() + "," + accesstoken.getTokenSecret());
		this.writer.newLine();
	}
	
}
