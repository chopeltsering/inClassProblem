package inClassProblem3_part2;

public class BidirectionalChannel implements Channel{
	
	Channel ch1;
	Channel ch2;
	boolean firstTimeForThread1 = true;
	boolean firstTimeForThread2 = true;
	long ch1ThreadPut;
	long ch1ThreadTake;
	long ch2ThreadPut;
	long ch2ThreadTake;
	
	
	public BidirectionalChannel(Channel ch1, Channel ch2){
		this.ch1 = ch1;
		this.ch1 = ch2;
	}
	
	public void put(Object o) throws InterruptedException {
		if(firstTimeForThread1){
			ch1ThreadPut = Thread.currentThread().getId();
			ch1.put(o);
			firstTimeForThread1 = false;
			
		}else if(Thread.currentThread().getId() == ch1ThreadPut){
			ch1.put(o);
			
		}else if(firstTimeForThread2){
			ch2ThreadPut = Thread.currentThread().getId();
			ch2.put(o);
			firstTimeForThread2 = false;
			
		}else if(Thread.currentThread().getId() == ch2ThreadPut){
			ch2.put(o);
		}
		
	}

	
	public Object take() throws InterruptedException {
		if(firstTimeForThread1){
			ch1ThreadPut = Thread.currentThread().getId();
			ch1.take();
			firstTimeForThread1 = false;
			
		}else if(Thread.currentThread().getId() == ch1ThreadTake){
			ch1.take();
			
		}else if(firstTimeForThread2){
			ch2ThreadPut = Thread.currentThread().getId();
			ch2.take();
			firstTimeForThread2 = false;
			
		}else if(Thread.currentThread().getId() == ch2ThreadTake){
			ch2.take();
		}
		return null;
	}
	
//	public boolean put(Thread thread){
//		if(firstTime){
//			return true;
//		}else{
//			return false;
//		}
//	}

}
