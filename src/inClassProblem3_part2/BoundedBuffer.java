
package inClassProblem3_part2;
public class BoundedBuffer implements Channel
{  
    protected int size;
    private Object[] buffer;
    private int in = 0, out = 0, count = 0; 
    String name;
    
    
    public BoundedBuffer(int size, String name){
    	this.name = name;
    	this.size = size;
    	buffer = new Object [this.size];
    }

	public synchronized void put(Object o) throws InterruptedException {
		while (count == size){
			System.out.println(""+ Thread.currentThread().getName() + " is waiting" + " on "+ name + " to put");
			wait();
		}
	    buffer[in] = o;
	    //System.out.println(buffer[in]);
	    in = (in + 1) % size;
	    if(count++ == 0){
	    	notifyAll();
	    	
	    }
	}

	
	public synchronized Object take() throws InterruptedException {
		while(count ==0){
			System.out.println(""+ Thread.currentThread().getName() + " is waiting" + " on "+ name + " to take");
			wait();
		}
		Object o = buffer[out];
		//System.out.println(buffer[out]);
		out = (out +1) % size;
		if(count-- == size)
			notifyAll();
		return o;
	}
    
}