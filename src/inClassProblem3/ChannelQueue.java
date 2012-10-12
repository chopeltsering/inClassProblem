package inClassProblem3;

public class ChannelQueue implements Channel{
	
	Queue unbounded;
	
	public ChannelQueue(Queue unbounded){
		this.unbounded = unbounded;
	}
	
	public void put(Object o) throws InterruptedException {
		unbounded.put(o);
		
	}
	
	public Object take() throws InterruptedException {
		return unbounded.take();
	}

}
