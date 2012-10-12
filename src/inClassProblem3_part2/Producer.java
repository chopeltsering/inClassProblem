package inClassProblem3_part2;

public class Producer implements Runnable{

	Channel ch;
	
	public Producer(Channel ch){
		this.ch = ch;
	}
	public void run() {
		
		for(int i = 0; i < 10; i++) {
            Integer item = new Integer(i);
            System.out.println(Thread.currentThread().getName() + " produced " + item);
            try {
				ch.put(i);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
//            try {
//                Thread.sleep(900);
//            } catch (InterruptedException e) {}
        }
		
	}

}
