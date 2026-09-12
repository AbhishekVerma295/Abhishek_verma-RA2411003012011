public class Bank {

    private NotificationService notificationService;
    private AccountRepository repository;

    public Bank(NotificationService notificationService, AccountRepository repository) {
        this.notificationService = notificationService;
        this.repository = repository;
    }

    public void deposit(BankAccount account, double amount) {
        if (account.deposit(amount)) {
            notificationService.send("Deposit successful. New balance: " + account.getBalance());
            repository.save(account);
        }
    }

    public void withdraw(BankAccount account, double amount, Integer pin) {
        if (account.withdraw(amount, pin)) {
            notificationService.send("Withdrawal successful. New balance: " + account.getBalance());
            repository.save(account);
        }
    }

    public double interest(BankAccount account, InterestPolicy policy) {
        return policy.calculate(account.getBalance());
    }
}
