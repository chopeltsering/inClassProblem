package baulkReentrantLock;

public class BaulkReentrantLock {

	Thread lockOwner = null;
	final int maxAttempts = 10;
	int numberAttempts = 0;

	public synchronized void acquire() throws BaulkException {
		if (lockOwner.equals(null))
			lockOwner = Thread.currentThread();

		if (lockOwner != Thread.currentThread()) {
			numberAttempts++;

			if (numberAttempts == maxAttempts) {
				numberAttempts = 0;
				throw new BaulkException();
			}

			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void release() {
		lockOwner = null;
		numberAttempts = 0;
		notify();
	}
}
