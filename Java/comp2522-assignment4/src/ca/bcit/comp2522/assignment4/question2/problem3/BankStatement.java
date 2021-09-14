package ca.bcit.comp2522.assignment4.question2.problem3;

import java.util.ArrayList;

public class BankStatement {
    private final String name;  // account name
    private Double balance;  //  account balance
    private final ArrayList<Transaction> transactionList = new ArrayList<>();

    public BankStatement(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public boolean addTransaction(Double amount, String type) {
        if (Transaction.TRANSACTION_WITHDRAW.equals(type)) {
            this.balance -= amount;
        } else if (Transaction.TRANSACTION_DEPOSIT.equals(type)) {
            this.balance += amount;
        } else {
            return false;
        }

        Transaction transaction = new Transaction(transactionList.size(), type, amount);
        transactionList.add(transaction);

        return true;
    }


    public Double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public final ArrayList<Transaction> getTransactions() {
        return transactionList;
    }

}
