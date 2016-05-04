package Event;

public class Event {
	private String name;
	private String source;
	private String content;
	private String date;
	
	public Event(String arg0, String arg1, String arg2, String arg3) {
		this.name = arg0;
		this.source = arg1;
		this.content = arg2;
		this.date = arg3;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getDate() {
		return this.date;
	}

}
