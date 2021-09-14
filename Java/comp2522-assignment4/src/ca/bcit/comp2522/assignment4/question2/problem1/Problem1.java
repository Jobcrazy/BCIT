package ca.bcit.comp2522.assignment4.question2.problem1;

public class Problem1 {
    public void test(){
        Invoice invoice = new Invoice();

        invoice.addItem("Tomato", 13.99f);
        invoice.addItem("Potato", 15.92f);
        invoice.addItem("Soap", 21.56f);

        invoice.printAllItems();

        System.out.printf("Total: %.2f\n", invoice.getTotalPrice());
    }
}
