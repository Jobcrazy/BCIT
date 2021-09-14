package ca.bcit.comp2522.assignment4.question2.problem3;

public class Transaction {
    private final int id; // transaction id
    private final String type; // transaction method(withdraw, deposit)
    private final Double amount; // amount money for the transaction
    public static final String TRANSACTION_DEPOSIT = "Deposit";
    public static final String TRANSACTION_WITHDRAW = "Withdraw";

    public Transaction(int id, String method, Double amount) {
        this.id = id;
        this.type = method;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public Double getAmount() {
        return this.amount;
    }
}
