
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Core.Message;
import Core.Users;
import Event.LucciolaEvent;
import Plugin.PluginLoader;
import Setting.Configure;
import Gui.*;
import javafx.application.*;

public class Lucciola {
	
	private static Users users;
	private static Message message;
	private static Configure configure;
	private static LucciolaEvent lucciolaEvent;
	private static PluginLoader pluginLoader;
	private static GuiCore guiCore;
	private static ExecutorService pool;
	
	public static void boot() {
		pool = Executors.newCachedThreadPool();
		lucciolaEvent = new LucciolaEvent(pool);
		configure = new Configure(lucciolaEvent);
		message = new Message();
		users = new Users(lucciolaEvent, message, configure, pool);
		guiCore = new GuiCore();
		pluginLoader = new PluginLoader();
		guiCore.setLucciolaEvent(lucciolaEvent);
		users.startUserStream();
	}
	
	public static void shutdown() {
		pool.execute(() -> {try {
			users.shutdownUserStream();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		pool.execute(() -> {try {
			configure.writeConfigure();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		pool.shutdown();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boot();
		Application.launch(guiCore.getClass(), args);
		shutdown();
	}

}
