package ca.bcit.comp2522.assignment5.question1;

import ca.bcit.comp2522.assignment5.question0.Product;

public class Drink extends Product {
    private final Boolean isDrinkDiet;
    private final Integer drinkSize;

    public Drink(Integer id, String name, Float price, String country, Boolean diet, Integer size) {
        super(id, name, price, country);
        isDrinkDiet = diet;
        drinkSize = size;
    }

    public Boolean getDrinkDiet() {
        return isDrinkDiet;
    }

    public Integer getDrinkSize() {
        return drinkSize;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.printf("IsDrinkDiet: %s\nDrinkSize: %d\n",
                getDrinkDiet() ? "Yes":"No", getDrinkSize());
    }
}
