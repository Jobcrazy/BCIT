package ca.bcit.comp2522.assignment5.question1;

import ca.bcit.comp2522.assignment5.question0.Product;

public class Food extends Product {
    private final Integer foodCalorie;
    private final Integer foodSize;
    private final String[] foodIngredients;

    public Food(Integer id, String name, Float price, String country,
                Integer calorie, Integer size, String[] ingredients) {
        super(id, name, price, country);
        foodCalorie = calorie;
        foodSize = size;
        foodIngredients = ingredients;
    }

    public Integer getFoodCalorie() {
        return foodCalorie;
    }

    public Integer getFoodSize() {
        return foodSize;
    }

    public String[] getFoodIngredients() {
        return foodIngredients;
    }

    @Override
    public Float getProductPrice() {
        return foodSize * super.getProductPrice();
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.printf("Calorie: %d\nSize: %d\nIngredients: ",
                getFoodCalorie(), getFoodSize());

        for (int index = 0; index < foodIngredients.length; index++) {
            if (foodIngredients.length - 1 != index) {
                System.out.printf("%s, ", foodIngredients[index]);
            } else {
                System.out.printf("%s", foodIngredients[index]);
            }
        }

        System.out.println();
    }
}
