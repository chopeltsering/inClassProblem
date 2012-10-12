package inClassProblem3;

import java.util.ArrayList;
import java.util.List;

public class Queue implements Channel{

	protected List<Object> list;
	
	public Queue(){
		list = new ArrayList<Object>();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public synchronized void put(Object o) throws InterruptedException {
		list.add(o);
		notify();
	}

	
	public synchronized Object take() throws InterruptedException {
		while(list.isEmpty()){
			System.out.println(""+ Thread.currentThread().getName() + " is waiting");
			wait();
		}
			
		return list.remove(0);
	}
	
}
