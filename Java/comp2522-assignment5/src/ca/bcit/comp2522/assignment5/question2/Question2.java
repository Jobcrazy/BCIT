package ca.bcit.comp2522.assignment5.question2;

import ca.bcit.comp2522.assignment5.question0.Product;
import ca.bcit.comp2522.assignment5.question1.Cloth;
import ca.bcit.comp2522.assignment5.question1.Drink;
import ca.bcit.comp2522.assignment5.question1.Food;
import ca.bcit.comp2522.assignment5.question1.Material;

public class Question2 {
    public void test(){
        ShoppingCart cart = new ShoppingCart();

        Product product = new Drink(412, "Pepsi", 2.0f, "USA", Boolean.FALSE, 150);
        cart.addProduct(product, 3);

        product = new Drink(183, "Ginger Zero", 3.0f, "Canada", Boolean.TRUE, 200);
        cart.addProduct(product, 1);

        product = new Food(100, "Chicken", 8.0f, "Canada", 350, 4,
                new String[]{"chicken", "oil", "cheese"});
        cart.addProduct(product, 2);

        product = new Food(101, "Pasta", 18.0f, "Canada", 250, 3,
                new String[]{"chicken", "oil", "cheese"});
        cart.addProduct(product, 2);

        product = new Cloth(701, "T-Shirt", 15.0f, "China",
                new Material[]{new Material(10, "Cotton"), new Material(11, "Nylon")});
        cart.addProduct(product, 1);

        cart.printAllItems();

        cart.printAllItemNames();

        cart.printTotalPrice();
    }
}
