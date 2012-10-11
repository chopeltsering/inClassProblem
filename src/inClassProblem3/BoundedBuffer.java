
package inClassProblem3;
public class BoundedBuffer
{  
    public static final int SIZE = 5;
    private Object[] buffer = new Object[SIZE];
    private int inIndex = 0, outIndex = 0, count = 0;
 

    public boolean addLast(Object item)
    {
	    if (count == SIZE) return false; // full
	    buffer[inIndex] = item;
	    inIndex = (inIndex + 1) % SIZE;
//	    count++;
//	    if (count == SIZE)
//	    	writeable = false; // now full
//	    else if (count == 1) // was empty
//	    	readable = true;
	    return true;
    }

    public Object removeFirst()
    {
	    Object item;
	    if (count == 0) return null; // empty
	    item = buffer[outIndex];
	    outIndex = (outIndex + 1) % SIZE;
//	    count--;
//	    if (count == 0)
//	    	readable = false; // now empty
//	    else if (count == SIZE-1) // was full
//	    	writeable = true;
	    return item;
    }
    public int getCount()
    {
    	return count;
    }
    
}