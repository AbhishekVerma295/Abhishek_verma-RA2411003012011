public class StatementGenerator {

    public String generate(BankAccount account) {
        String statement = "---- Statement for Account #" + account.getAccountNumber()
                + " (" + account.getName() + ") ----\n";

        for (String entry : account.getTransactionLog()) {
            statement += entry + "\n";
        }

        statement += "Current Balance: Rs. " + account.getBalance() + "\n";
        statement += "-----------------------------------------------------";

        return statement;
    }
}
