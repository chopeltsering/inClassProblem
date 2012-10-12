package inClassProblem3;

public class ChannelArray implements Channel{

	BoundedBuffer bounded;
	
	public ChannelArray(BoundedBuffer bounded){
		this.bounded = bounded;
	}
	
	public void put(Object o) throws InterruptedException {
		bounded.put(o);
		
	}

	
	public Object take() throws InterruptedException {
		return bounded.take();
	}

}
