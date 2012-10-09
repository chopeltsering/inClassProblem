package inClassProblem3;

public interface Channel {
	public void put(Object o) throws InterruptedException;
	
	public Object take() throws InterruptedException;
		
}
