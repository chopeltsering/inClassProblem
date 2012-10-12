package inClassProblem3_part2;

public class Consumer implements Runnable{

	Channel ch;
	
	public Consumer(Channel ch){
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
	            
	            Object item2 = null;
				try {
					item2 = ch.take();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
	            System.out.println(Thread.currentThread().getName() + " consumed " + item2);
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {}
	        }
		
	}

}
