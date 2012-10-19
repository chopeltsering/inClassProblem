package inClassProblem4_part1_byChopel;

import java.util.HashMap;

public class Reactor {
	
	HashMap<String, EventHandler> storage;
	
	public Reactor(){
		 storage = new HashMap<String, EventHandler>(); 
	}
	
	public void registerHandler(String eventName, EventHandler eh){
		storage.put(eventName, eh);
	}
	
	public void removeHandler(String eventName, EventHandler eh){
		storage.remove(eventName);
	}
	
	public void dispatch(){
		
	}
}
