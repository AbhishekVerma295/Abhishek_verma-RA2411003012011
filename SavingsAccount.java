public class SavingsAccount extends BankAccount implements Withdrawable {

    public SavingsAccount(int accountNumber, String name, int age, double balance) {
        super(accountNumber, name, age, balance, "Savings");
    }

    @Override
    public boolean withdraw(double amount) {
        return super.withdraw(amount, null);
    }
}
