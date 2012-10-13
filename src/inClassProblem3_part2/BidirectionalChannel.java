package inClassProblem3_part2;

public class BidirectionalChannel implements Channel{
	
	Channel ch1;
	Channel ch2;
<<<<<<< HEAD
	boolean firstTimeForThread1 = true;
	boolean firstTimeForThread2 = true;
	long ch1ThreadPut;
	long ch1ThreadTake;
	long ch2ThreadPut;
	long ch2ThreadTake;
=======
	long firstThread;
	boolean enteredForFirstTime;
>>>>>>> d4411780979501be5a07da91ec1c4d309a416b8c
	
	
	public BidirectionalChannel(Channel ch1, Channel ch2){
		this.ch1 = ch1;
		this.ch2 = ch2;
	}
	
<<<<<<< HEAD
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
=======
	public  void put(Object o) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() +" is in put method of wrapping channel class");
		synchronized(this){
			if(enteredForFirstTime){
				firstThread = Thread.currentThread().getId();
				ch1.put(o);
			}else 
				if(Thread.currentThread().getId()==firstThread)
					ch1.put(o);
				else
					ch2.put(o);
>>>>>>> d4411780979501be5a07da91ec1c4d309a416b8c
		}
		
	}

	
<<<<<<< HEAD
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
=======
	public  Object take() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() +" is in take method of wrapping channel class");
		synchronized(this){
			if(enteredForFirstTime){
				firstThread = Thread.currentThread().getId();
				ch2.take();
			}else
				if(Thread.currentThread().getId() == firstThread){
					ch2.take();
					System.out.println("consume from Channel 2");
				}
					
				else{
					ch1.take();
					System.out.println("consume from Channel 1");
				}
		}
		
				
		return null;
	}
>>>>>>> d4411780979501be5a07da91ec1c4d309a416b8c

}
