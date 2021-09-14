package ca.bcit.comp2522.assignment10.main;

import ca.bcit.comp2522.assignment10.question1.Question1;
import ca.bcit.comp2522.assignment10.question2.Question2;
import ca.bcit.comp2522.assignment10.question3.Question3;
import ca.bcit.comp2522.assignment10.question4.Question4;
import ca.bcit.comp2522.assignment10.question5.Question5;

public class Assignment10Driver {
    public static void run(){
        Question1 question1 = new Question1();
        question1.test();

        Question2 question2 = new Question2();
        question2.test();

        Question3 question3 = new Question3();
        question3.test();

        Question4 question4 = new Question4();
        question4.test();

        Question5 question5 = new Question5();
        question5.test();
    }
}
