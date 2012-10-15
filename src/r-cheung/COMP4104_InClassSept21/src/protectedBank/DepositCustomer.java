package protectedBank;


public class DepositCustomer implements Runnable {
	String name;
	ProtectedBankAccount bankAccount;
	java.util.Random r = new java.util.Random();

	public DepositCustomer(ProtectedBankAccount bankAccount, String name) {
		this.bankAccount = bankAccount;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			
			// System.out.println(name + ": Before transaction, balance is " +
			// bankAccount.balance());
			bankAccount.deposit(1);
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
