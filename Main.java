/*
 * Task 5 — wrap-up:
 * I ended up with 4 classes: BankAccount, AccountRepository, NotificationService
 * and StatementGenerator. It is easier to test because each class has one job,
 * so I can test deposit and withdraw without a database or an email server.
 * StatementGenerator just returns a String, so I can compare it instead of
 * reading printed output.
 */
public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(101, "Ravi", 17, 200, "Savings");

        AccountRepository repository = new AccountRepository();
        NotificationService notificationService = new NotificationService();
        StatementGenerator statementGenerator = new StatementGenerator();

        account.setPin(1234);

        account.deposit(1000);
        notificationService.send("Deposit successful. New balance: " + account.getBalance());
        repository.save(account);

        account.withdraw(500, 1234);
        notificationService.send("Withdrawal successful. New balance: " + account.getBalance());
        repository.save(account);

        account.withdraw(500, 9999); // wrong PIN, should fail

        System.out.println(statementGenerator.generate(account));
    }
}
