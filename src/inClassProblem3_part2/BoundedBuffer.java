
package inClassProblem3_part2;
public class BoundedBuffer implements Channel
{  
    protected int size;
    private Object[] buffer;
    private int in = 0, out = 0, count = 0;
    
    public BoundedBuffer(int size){
    	this.size = size;
    	buffer = new Object [this.size];
    }

	public synchronized void put(Object o) throws InterruptedException {
		while (count == size){
			System.out.println(""+ Thread.currentThread().getName() + " is waiting");
			wait();
		}
	    buffer[in] = o;
	    in = (in + 1) % size;
	    if(count++ == 0){
	    	notifyAll();
	    }
	}

	
	public synchronized Object take() throws InterruptedException {
		while(count ==0){
			System.out.println(""+ Thread.currentThread().getName() + " is waiting");
			wait();
		}
		Object o = buffer[out];
		out = (out +1) % size;
		if(count-- == size)
			notifyAll();
		return o;
	}
    
}