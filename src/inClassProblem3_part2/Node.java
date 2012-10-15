package inClassProblem3_part2;

public class Node implements Runnable{
	
	Channel ch;
	
	public Node(Channel ch){
		this.ch = ch;
	}
	
public void run() {
		
		for(int i = 0; i < 10; i++) {
            Integer item1 = new Integer(i);
            System.out.println(Thread.currentThread().getName() + " produced " + item1);
            try {
				ch.put(i);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {}
            
            Object item2 = null;
			try {
				item2 = ch.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " consumed " + item2);
            
        }
		
	}

}
