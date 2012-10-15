package modifiedReentrantLock;

import java.util.Timer;
import java.util.TimerTask;

import generic.Sync;

public class TimedReentrantLock implements Sync {

	private Thread lockOwner = null;
	private int maxAquiredTime;
	private Timer timer;

	public TimedReentrantLock(int maxAquiredTime) {
		this.maxAquiredTime = maxAquiredTime;
		timer = new Timer();
	}

	public synchronized void acquire() {
		if (lockOwner == null) {
			lockOwner = Thread.currentThread();
			timer.schedule(new UnlockTask(), maxAquiredTime);
		}

		if (lockOwner != Thread.currentThread()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class UnlockTask extends TimerTask {
		@Override
		public void run() {
			release();
		}
	}

	public synchronized void release() {
		lockOwner = null;
		try {
			timer.cancel();
		} catch(IllegalStateException e) {
			
		}
		notify();
	}

	@Override
	public synchronized void attempt(int msecs) {
		// TODO Auto-generated method stub
	}
}
