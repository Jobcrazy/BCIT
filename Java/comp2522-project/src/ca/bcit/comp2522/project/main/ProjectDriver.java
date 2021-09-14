package ca.bcit.comp2522.project.main;

import ca.bcit.comp2522.project.question1.Question1;
import ca.bcit.comp2522.project.question2.Question2;

public class ProjectDriver {
    public static void run() {
        Question1 question1 = new Question1();
        question1.test();

        Question2 question2 = new Question2();
        question2.test();
    }
}
