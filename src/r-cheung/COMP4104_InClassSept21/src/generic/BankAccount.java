package generic;

public class BankAccount {
	int balance;
	java.util.Random r = new java.util.Random();

	public BankAccount(int initialBalance) {
		balance = initialBalance;
	}

	private int getBalance() {
		return balance;
	}

	private void setBalance(int i) {
		balance = i;
	}

	public int balance() {
		return getBalance();
	}

	public void deposit(int amount) {
		setBalance(getBalance() + amount);
		delay();
		System.out.println(this + " deposit: " + amount + " balance: "
				+ getBalance());
	}

	public boolean withdraw(int amount) {
		if (getBalance() < amount)
			return false;
		setBalance(getBalance() - amount);
		delay();
		System.out.println(this + " withdraw: " + amount + " balance: "
				+ getBalance());
		return true;
	}

	void delay() {
		try {
			Thread.sleep(r.nextInt(90) + 10);
		} catch (java.lang.InterruptedException e) {
		}

	}
}
