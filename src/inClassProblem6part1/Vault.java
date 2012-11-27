package inClassProblem6part1;

import java.util.HashMap;

public class Vault {
	HashMap<String, Object> storage;
	
	public Vault(int capacity){
		storage = new HashMap<>();
	}
	
	public Vault(){
		storage = new HashMap<>();
	}
	
	public void addObject(String name, Object object){
		storage.put(name, object);
	}
	
	public Object getObject(String name) throws InterruptedException{
		return storage.get(name);
	}
}
