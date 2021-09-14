package ca.bcit.comp2522.assignment5.question1;

public class Question1 {
    public void test(){
        Drink drink = new Drink(412, "Pepsi", 2.0f, "USA", Boolean.FALSE, 150);
        drink.printInfo();

        Food food = new Food(100, "Chicken", 8.0f, "Canada", 350, 4,
                new String[]{"chicken", "oil", "cheese"});
        food.printInfo();

        Cloth cloth = new Cloth(701, "T-Shirt", 15.0f, "China",
                new Material[]{new Material(10, "Cotton"), new Material(11, "Nylon")});
        cloth.printInfo();
    }
}
