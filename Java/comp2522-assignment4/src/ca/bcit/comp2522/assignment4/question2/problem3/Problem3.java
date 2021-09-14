package ca.bcit.comp2522.assignment4.question2.problem3;

public class Problem3 {
    public void test() {
        BankStatement statement = new BankStatement("Joon", 10000.0);

        statement.addTransaction(1000.0, Transaction.TRANSACTION_DEPOSIT);
        statement.addTransaction(1000.0, Transaction.TRANSACTION_DEPOSIT);
        statement.addTransaction(5000.0, Transaction.TRANSACTION_WITHDRAW);
        if (statement.addTransaction(1000.0, Transaction.TRANSACTION_DEPOSIT)) {
            System.out.println("Successful!");
        }

        for (Transaction transaction : statement.getTransactions()) {
            System.out.printf("%X\t| %s\t| $%.2f\n", transaction.getId(),
                    transaction.getType(), transaction.getAmount());
        }

        System.out.println(statement.getName() + "'s Account : $" + statement.getBalance());
    }
}
