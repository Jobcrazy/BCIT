package ca.bcit.comp2522.assignment5.question2;

import ca.bcit.comp2522.assignment5.question0.Product;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> listProduct = new ArrayList<>();

    public void addProduct(Product product, int amount) {
        for (int index = 0; index < amount; index++) {
            listProduct.add(product);
        }
    }

    public void printAllItems() {
        for (Product product : listProduct) {
            product.printInfo();
        }
    }

    public void printAllItemNames() {
        System.out.println("----All Items----");
        for (Product product : listProduct) {
            System.out.println(product.getProductName());
        }
    }

    public void printTotalPrice() {
        System.out.println("--------");
        Float total = 0.0f;

        for (Product product : listProduct) {
            total += product.getProductPrice();
        }

        System.out.printf("Total: %.2f", total);
    }


}
