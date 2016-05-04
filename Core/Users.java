package Core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import twitter4j.*;
import Event.LucciolaEvent;
import OAuth.*;
import Setting.Configure;

public class Users {
	
	private LucciolaEvent lucciolaEvent;
	private String fileName = "bin/Core/accounts";
	private BufferedReader reader;
	private BufferedWriter writer;
	private Map<String, Twitter> userList;
	private Twitter currentUser;
	private TwitterStream stream;
	private Message message;
	private Configure configure;
	private ExecutorService pool;
	
	public Users(LucciolaEvent arg0, Message arg1, Configure arg2, ExecutorService arg3) {
		lucciolaEvent = arg0;
		message = arg1;
		configure = arg2;
		pool = arg3;
		userList = new HashMap<String, Twitter>(0);
		try {
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"));
			loadUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
		}
	}
	
	private void loadUsers() throws IOException {
		String buf;
		while((buf = reader.readLine()) != null) {
			try {
				Twitter tmp = new UserLoginer(buf.split(",")[0], buf.split(",")[1]).login();
				userList.put(Long.toString(tmp.getId()), tmp);
			} catch (IllegalStateException | TwitterException e) {
				// TODO Auto-generated catch block
				lucciolaEvent.onException(e.getMessage());
			}
		}
	}
	
	public Twitter addUser() {
		try {
			Twitter tmp = new UserCreater(writer).createUser();
			userList.put(Long.toString(tmp.getId()), tmp);
			return tmp;
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			lucciolaEvent.onException(e.getMessage());
			return null;
		}
	}
	
	public Twitter getUser(String arg0) {
		Twitter ans = null;
		for(Map.Entry<String, Twitter> e : userList.entrySet()) {
			if(e.getKey().equals(arg0))ans = e.getValue();
		}
		return ans;
	}
	
	public void startUserStream() {
		if(configure.getConfig("CurrentUser").equals(" ")) {
			try {
				configure.setConfig("CurrentUser", Long.toString(addUser().getId()));
			} catch (IllegalStateException | TwitterException e) {
				// TODO Auto-generated catch block
				lucciolaEvent.onException(e.getMessage());
			}
		}
		currentUser = this.getUser(configure.getConfig("CurrentUser"));
		stream = new TwitterStreamFactory(currentUser.getConfiguration()).getInstance();
		stream.addListener(new MessageStreamListener(lucciolaEvent, message, currentUser));
		stream.user();
		lucciolaEvent.addListener(new PostTweet(currentUser, lucciolaEvent, pool));
	}
	
	public void shutdownUserStream() throws IOException {
		stream.shutdown();
		writer.close();
	}
	
}
