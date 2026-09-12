public class FixedDepositAccount extends BankAccount {

    public FixedDepositAccount(int accountNumber, String name, int age, double balance) {
        super(accountNumber, name, age, balance, "FixedDeposit");
    }
}
