public class CurrentAccount extends BankAccount implements Withdrawable {

    public CurrentAccount(int accountNumber, String name, int age, double balance) {
        super(accountNumber, name, age, balance, "Current");
    }

    @Override
    public boolean withdraw(double amount) {
        return super.withdraw(amount, null);
    }
}
