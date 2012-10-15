package protectedBank;

public class WithdrawCustomer implements Runnable {
	String name;
	ProtectedBankAccount bankAccount;
	java.util.Random r = new java.util.Random();

	public WithdrawCustomer(ProtectedBankAccount bankAccount, String name) {
		this.bankAccount = bankAccount;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			// System.out.println(name + ": Before transaction, balance is " +
			// bankAccount.balance());
			System.out.println("Withdraw: " + bankAccount.withdraw(4));

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
