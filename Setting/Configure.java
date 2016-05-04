package Setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import Event.LucciolaEvent;

public class Configure {
	
	private String fileName = "bin/Setting/lucciola.conf";
	private BufferedReader reader;
	private BufferedWriter writer;
	private Map<String, String> config;
	private LucciolaEvent lucciolaEvent;
	
	public Configure(LucciolaEvent arg0) {
		lucciolaEvent = arg0;
		config = new HashMap<String, String>(0);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			readConfigure();
		} catch(Exception e) {
			lucciolaEvent.onException(e.getMessage());
		}
	}
	
	private void readConfigure() throws IOException {
		String buf;
		while((buf = reader.readLine()) != null) {
			config.put(buf.split(":")[0], buf.split(":")[1]);
		}
	}
	
	public void writeConfigure() throws IOException {
		writer =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, false), "utf-8"));
		for(Map.Entry<String, String> e : config.entrySet()) {
			writer.write(e.getKey() + ":" + e.getValue());
			writer.newLine();
		}
		writer.close();
	}
	
	public String getConfig(String arg0) {
		String ans = null;
		for(Map.Entry<String, String> e : config.entrySet()) {
			if(e.getKey().equals(arg0))ans = e.getValue();
		}
		return ans;
	}
	
	public void addConfig(String arg0, String arg1) {
		this.config.put(arg0, arg1);
	}
	
	public void setConfig(String arg0, String arg1) {
		for(Map.Entry<String, String> e : config.entrySet()) {
			if(e.getKey().equals(arg0))e.setValue(arg1);
		}
	}
	
}
