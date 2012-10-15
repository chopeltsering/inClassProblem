package protectedBank;

import generic.BankAccount;

public class ProtectedBankAccount {
	private BankAccount bankAccount;
	private int desiredWithdrawAmount = 0;

	public ProtectedBankAccount(int amount) {
		bankAccount = new BankAccount(amount);
	}

	public synchronized int balance() {
		return bankAccount.balance();
	}

	public synchronized void deposit(int amount) {
		bankAccount.deposit(amount);

		if (enoughFunds())
			notifyAll();
	}

	public synchronized boolean withdraw(int amount) {
		boolean withdrawable;

		desiredWithdrawAmount += amount;

		if (!enoughFunds()) {
			try {
				System.out.println("Going to wait");
				wait();
				System.out.println("Done waiting");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		withdrawable = bankAccount.withdraw(amount);
		desiredWithdrawAmount -= amount;

		return withdrawable;
	}

	private boolean enoughFunds() {
		return (bankAccount.balance() > desiredWithdrawAmount);
	}
}
