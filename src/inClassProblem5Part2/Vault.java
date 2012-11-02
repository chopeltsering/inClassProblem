package inClassProblem5Part2;

import java.util.concurrent.ArrayBlockingQueue;

public class Vault {
	
	ArrayBlockingQueue<String> list;
	
	public Vault(int capacity){
		list = new ArrayBlockingQueue<String>(capacity);	
	}
	
	public void addObject(String item){
		list.add(item);
	}
	
	public String getObject() throws InterruptedException{
		return list.take();
	}

} 
