package inClassProblem4_part1_byChopel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

public class Reactor {
	
	HashMap<String, EventHandler> storage;
	
	public Reactor(){
		 storage = new HashMap<String, EventHandler>(); 
	}
	
	public void registerHandler(String pathToFile, String key){
		EventHandler handler= loadFromFile(pathToFile, key);
		storage.put(key, handler);
	}
	
	public void removeHandler(String key, EventHandler eh){
		storage.remove(key);
	}
	
	public void dispatch(Event event) throws IOException{
		//handle_events(sock);
	}
	
	public void handle_events(Socket sock){ 
		for(;;){
			Event event = wait_for_events();
			
			EventHandler handler = identify_event_handler(event);
			handler.HandleEvent(event);
		}
	}
	
	public Event wait_for_events(Socket sock) throws IOException{
		InputStreamReader IR = new InputStreamReader(sock.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		
	}
	
	public EventHandler loadFromFile(String pathToFile, String key){
		Properties p = new Properties();
		EventHandler handler = null;
		try {
			p.load(new FileInputStream(pathToFile)); // need to check if fileName correspond to correct path
			String className = p.getProperty(key);
			//System.out.println(className);
			Class<?>cla = Class.forName(className);
			handler = (EventHandler)cla.newInstance();
			//System.out.println(object.getName());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return handler;
	}
}
