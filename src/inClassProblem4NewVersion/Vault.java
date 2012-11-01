package inClassProblem4NewVersion;

import java.util.concurrent.ArrayBlockingQueue;

public class Vault {
	
	ArrayBlockingQueue<TestObject> list;
	
	public Vault(int capacity){
		list = new ArrayBlockingQueue<TestObject>(capacity);	
	}
	
	public void addObject(TestObject item){
		list.add(item);
	}
	
	public TestObject getObject() throws InterruptedException{
		return list.take();
	}

} 
