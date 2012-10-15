package nonBlockStructuredLock;

import generic.Sync;

public class NonBlockStructuredLock implements Sync{
	Thread lockOwner = null;

	public synchronized void acquire() {
		if (lockOwner == null)
			lockOwner = Thread.currentThread();

		if (lockOwner != Thread.currentThread()) {
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
		notify();
	}

	@Override
	public synchronized void attempt(int msecs) {
		while (lockOwner != null) {
			try {
				Thread.sleep(msecs);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		acquire();
	}
}
