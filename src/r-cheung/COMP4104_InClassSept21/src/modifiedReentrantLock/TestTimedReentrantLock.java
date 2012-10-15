package modifiedReentrantLock;

import generic.BankAccount;

public class TestTimedReentrantLock {

	 private static BankAccount bankAccount;
	 private static TimedReentrantLock timedReentrantock;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		bankAccount = new BankAccount(0);
		timedReentrantock = new TimedReentrantLock(1000);
		
		for (int i = 0; i < 10; i++) {
			new Thread(new DepositCustomer(bankAccount, "Deposit", timedReentrantock)).start();
		}

	}

}
