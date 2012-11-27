package inClassProblem5Part2;

import java.util.concurrent.ArrayBlockingQueue;

public class Vault {
	
	ArrayBlockingQueue<Object> list;
	
	public Vault(int capacity){
		list = new ArrayBlockingQueue<Object>(capacity);	
	}
	
	public void addObject(Object item){
		list.add(item);
	}
	
	public Object getObject() throws InterruptedException{
		return list.take();
	}

} 
