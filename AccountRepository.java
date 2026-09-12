public class AccountRepository {

    public void save(BankAccount account) {
        System.out.println("Saving account " + account.getAccountNumber() + " to database");
    }
}
