package inClassProblem3_part2;

public interface Channel {
	public void put(Object o) throws InterruptedException;
	
	public Object take() throws InterruptedException;
		
}
