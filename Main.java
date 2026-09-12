/*
 * Section 2, Task 5 — wrap-up:
 * For the Salary Account I only had to open and edit Main (to create the new
 * account and pick its policy). Everything else was brand new: SalaryAccount,
 * SalaryInterestPolicy, InterestPolicy, SavingsInterestPolicy and
 * CurrentInterestPolicy.
 * Zero existing policy classes were changed to add the Salary Account.
 *
 * Section 3, Task 2 — why Square/Rectangle breaks LSP:
 * Calling code that has a Rectangle assumes width and height are independent,
 * so setting one does not change the other. Square breaks that assumption
 * because setHeight also changes the width. So a Square cannot be used
 * everywhere a Rectangle is expected, which is what LSP asks for.
 *
 * Section 3, Task 5 — wrap-up:
 * Making FixedDepositAccount implement Withdrawable and throw an exception
 * compiles, but it breaks the LSP substitution rule: code holding a
 * Withdrawable must be able to use any Withdrawable without knowing which
 * one it is, and here one of them crashes instead of working. The exception
 * only moves the problem from compile time to run time, so every caller then
 * needs a special check for Fixed Deposits. The honest fix is to not give
 * FixedDepositAccount a contract it cannot fulfill.
 */
import java.util.ArrayList;
import java.util.List;

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

        // Section 3, Task 1 — Rectangle / Square warm-up
        Rectangle rectangle = new Square();
        rectangle.setWidth(10);
        rectangle.setHeight(20);
        System.out.println("Area of Square stored as Rectangle: " + rectangle.getArea());

        // Section 3, Task 4 — only withdrawable accounts go in the list
        SavingsAccount savingsAccount = new SavingsAccount(201, "Neha", 28, 3000);
        CurrentAccount currentAccount = new CurrentAccount(202, "Kiran", 35, 4000);
        FixedDepositAccount fixedDeposit = new FixedDepositAccount(203, "Deepa", 40, 10000);

        List<Withdrawable> withdrawables = new ArrayList<>();
        withdrawables.add(savingsAccount);
        withdrawables.add(currentAccount);

        for (Withdrawable withdrawable : withdrawables) {
            withdrawable.withdraw(1000);
        }

        System.out.println("Savings balance: Rs. " + savingsAccount.getBalance());
        System.out.println("Current balance: Rs. " + currentAccount.getBalance());
        System.out.println("Fixed deposit balance: Rs. " + fixedDeposit.getBalance());
    }
}
