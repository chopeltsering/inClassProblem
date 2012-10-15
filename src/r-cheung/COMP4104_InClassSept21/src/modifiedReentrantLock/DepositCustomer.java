package modifiedReentrantLock;

import generic.*;


public class DepositCustomer implements Runnable {
	private String name;
	private BankAccount bankAccount;
	private java.util.Random r = new java.util.Random();
	private TimedReentrantLock timedReentrantLock;

	public DepositCustomer(BankAccount bankAccount, String name, TimedReentrantLock timedReentrantLock) {
		this.bankAccount = bankAccount;
		this.name = name;
		this.timedReentrantLock = timedReentrantLock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			timedReentrantLock.acquire();
			// System.out.println(name + ": Before transaction, balance is " +
			// bankAccount.balance());
			bankAccount.deposit(1);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timedReentrantLock.release();
			// System.out.println(name + ": After transaction, balance is " +
			// bankAccount.balance());
			delay();
		}
	}

	private void delay() {
		try {
			Thread.sleep(r.nextInt(90) + 10);
		} catch (java.lang.InterruptedException e) {
		}
	}

	public String toString() {
		return name;
	}

}
