package Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class LucciolaEvent {
	private List<LucciolaListener> listeners;
	private ExecutorService pool;
	
	public LucciolaEvent(ExecutorService arg0) {
		this.pool = arg0;
		listeners = new ArrayList<LucciolaListener>(0);
	}
	
	public void addListener(LucciolaListener arg0) {
		listeners.add(arg0);
	}
	
	public void onEvent(String arg) {
		pool.execute(() -> {
			for(int i = 0; i < listeners.size(); i ++) {
				listeners.get(i).onEvent(arg);
			}
		});
	}
	
	public void onException(String arg) {
		pool.execute(() -> {
			for(int i = 0; i < listeners.size(); i ++) {
				int j = i;
				pool.execute(() -> {listeners.get(j).onException(arg);});
			}
		});
	}

}
