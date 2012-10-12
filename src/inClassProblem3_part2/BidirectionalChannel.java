package inClassProblem3_part2;

public class BidirectionalChannel implements Channel{
	
	Channel ch1;
	Channel ch2;
	Thread ch1ThreadPut;
	Thread ch1ThreadTake;
	Thread ch2ThreadPut;
	Thread ch2ThreadTake;
	
	
	public BidirectionalChannel(Channel ch1, Channel ch2){
		this.ch1 = ch1;
		this.ch1 = ch2;
	}
	
	public void put(Object o) throws InterruptedException {
		
		
	}

	
	public Object take() throws InterruptedException {
		return null;
	}
	
	public boolean put(Thread thread){
		if(firstTime){
			return true;
		}else{
			return false;
		}
	}

}
