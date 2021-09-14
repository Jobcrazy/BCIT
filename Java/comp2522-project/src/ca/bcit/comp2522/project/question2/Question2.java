package ca.bcit.comp2522.project.question2;

import java.util.function.Function;

public class Question2 {
    public void test() {
        System.out.println("-----Question 2-----");
        Game game = new Game("data/transformers.txt");
        game.loadGame(new TransformerParser(), new TransformerComparator());
    }
}
