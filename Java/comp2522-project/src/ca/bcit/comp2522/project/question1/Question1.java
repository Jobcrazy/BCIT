package ca.bcit.comp2522.project.question1;

public class Question1 {
    public void test() {
        System.out.println("-----Question 1-----");
        Lands lands = new Lands("data/castles.txt");
        lands.consumeLand(new LandConsumer());
    }
}
