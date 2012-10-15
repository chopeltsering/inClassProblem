package protectedBank;

public class TestProtectedBank {

	static ProtectedBankAccount bankAccount;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bankAccount = new ProtectedBankAccount(0);
		for (int i = 0; i < 10; i++) {
			new Thread(new DepositCustomer(bankAccount, "Deposit")).start();
			new Thread(new WithdrawCustomer(bankAccount, "Withdraw")).start();
		}
	}
}
