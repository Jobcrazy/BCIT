package ca.bcit.comp2522.assignment4.question2.problem1;

import java.util.ArrayList;

public class Invoice {
    private final ArrayList<Product> productList = new ArrayList<>();

    public void addItem(String name, Float price) {
        Product product = new Product(name, price);
        productList.add(product);
    }

    public void printAllItems(){
        for (Product product: productList){
            System.out.printf("%s: %.2f\n", product.getName(), product.getPrice());
        }
    }

    public Float getTotalPrice(){
        Float total = 0.0f;

        for (Product product: productList){
            total += product.getPrice();
        }

        return total;
    }
}
