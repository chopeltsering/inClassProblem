package inClassProblem3_part2;

public class BidirectionalChannel implements Channel{
	
	Channel ch1;
	Channel ch2;

	long firstThread;
	boolean enteredForFirstTime;
	
	public BidirectionalChannel(Channel ch1, Channel ch2){
		this.ch1 = ch1;
		this.ch2 = ch2;
	}
	

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
		}
		
	}

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

}
