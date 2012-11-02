package inClassProblem5Part1;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;

public class Vault {
	
	ArrayBlockingQueue<Serializable> list;
	
	public Vault(int capacity){
		list = new ArrayBlockingQueue<Serializable>(capacity);	
	}
	
	public void addObject(Serializable item){
		list.add(item);
	}
	
	public Serializable getObject() throws InterruptedException{
		return list.take();
	}

} 
