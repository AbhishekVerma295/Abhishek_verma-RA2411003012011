/*
 * Task 5 — wrap-up:
 * For the Salary Account I only had to open and edit Main (to create the new
 * account and pick its policy). Everything else was brand new: SalaryAccount,
 * SalaryInterestPolicy, InterestPolicy, SavingsInterestPolicy and
 * CurrentInterestPolicy.
 * Zero existing policy classes were changed to add the Salary Account.
 */
public class Main {

    public static void main(String[] args) {

        NotificationService notificationService = new EmailNotificationService();
        AccountRepository repository = new AccountRepository();
        StatementGenerator statementGenerator = new StatementGenerator();

        Bank bank = new Bank(notificationService, repository);

        BankAccount savings = new BankAccount(101, "Ravi", 17, 200, "Savings");
        savings.setPin(1234);

        bank.deposit(savings, 1000);
        bank.withdraw(savings, 500, 1234);
        bank.withdraw(savings, 500, 9999); // wrong PIN, should fail

        System.out.println(statementGenerator.generate(savings));

        InterestPolicy savingsPolicy = new SavingsInterestPolicy();
        System.out.println("Savings interest: Rs. " + bank.interest(savings, savingsPolicy));

        BankAccount current = new BankAccount(102, "Meera", 30, 5000, "Current");
        InterestPolicy currentPolicy = new CurrentInterestPolicy();
        System.out.println("Current interest: Rs. " + bank.interest(current, currentPolicy));

        SalaryAccount salary = new SalaryAccount(103, "Arjun", 25, 8000);
        InterestPolicy salaryPolicy = new SalaryInterestPolicy();
        System.out.println("Salary interest: Rs. " + bank.interest(salary, salaryPolicy));
    }
}
