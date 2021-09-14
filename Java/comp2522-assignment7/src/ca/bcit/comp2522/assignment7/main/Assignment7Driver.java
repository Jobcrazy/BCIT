package ca.bcit.comp2522.assignment7.main;

import ca.bcit.comp2522.assignment7.question1.Question1;
import ca.bcit.comp2522.assignment7.question2.Question2;
import ca.bcit.comp2522.assignment7.question3.Question3;
import ca.bcit.comp2522.assignment7.question4.Question4;
import ca.bcit.comp2522.assignment7.question5.Question5;

public class Assignment7Driver {
    public static void run() {
        // Question 1
        Question1 question1 = new Question1();
        question1.test();

        // Question 2
        Question2 question2 = new Question2();
        question2.test();

        // Question 3
        Question3 question3 = new Question3();
        question3.test();

        // Question 4
        Question4 question4 = new Question4();
        question4.test();

        // Question 5
        Question5 question5 = new Question5();
        question5.test();
    }
}
