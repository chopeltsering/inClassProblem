package inClassProblem3;

public class Consumer implements Runnable{

	Channel ch;
	
	public Consumer(Channel ch){
		this.ch = ch;
	}
	public void run() {
		
		 for(int i = 0; i < 10; i++) {
	            Object item = null;
				try {
					item = ch.take();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
	            System.out.println(Thread.currentThread().getName() + " consumed " + item);
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {}
	        }
		
	}

}
