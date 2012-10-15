package inClassProblem3_part2;

public class BidirectionalChannel implements Channel{
	
	Channel ch1;
	Channel ch2;

	long firstThread;
	boolean enteredForFirstTime = true;
	
	public BidirectionalChannel(Channel ch1, Channel ch2){
		this.ch1 = ch1;
		this.ch2 = ch2;
	}
	

	public  void put(Object o) throws InterruptedException {
		//System.out.println(Thread.currentThread().getName() +" is in put method of wrapping channel class");
		if(enteredForFirstTime){
			enteredForFirstTime = false;  // if this statement is few lines below it seems it gets entered by other threads before its set to false
			System.out.println("entered for the first time and putting in ch1, by : " + Thread.currentThread().getName());
			firstThread = Thread.currentThread().getId();
			ch1.put(o);
			
		}else if(Thread.currentThread().getId()==firstThread){
				ch1.put(o);
				System.out.println(" putting in ch1, by : " + Thread.currentThread().getName());
				
		}else{
			ch2.put(o);
			System.out.println(" putting in ch2, by : " + Thread.currentThread().getName());
				
		}
		
	}

	public  Object take() throws InterruptedException {
		//System.out.println(Thread.currentThread().getName() +" is in take method of wrapping channel class");
		Object item;
		if(enteredForFirstTime){
			firstThread = Thread.currentThread().getId();
			item = ch2.take();
		}else if(Thread.currentThread().getId() == firstThread){
			item = ch2.take();
			System.out.println("consume from Channel 2, by : " + Thread.currentThread().getName());
		}else{
			item = ch1.take();
			System.out.println("consume from Channel 1, by : " + Thread.currentThread().getName());
		}		
		return item;
	}

}
