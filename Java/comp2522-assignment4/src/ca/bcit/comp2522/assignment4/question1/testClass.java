package ca.bcit.comp2522.assignment4.question1;

import java.util.ArrayList;
import java.util.Arrays;

public class testClass {
    public void test() {
        Integer[] student1_grades = {80, 78, 65, 34};
        Student student1 = new Student(77889911, "Dave",
                new ArrayList<Integer>(Arrays.asList(student1_grades)));

        Student student2 = new Student(66553322, "James");

        student2.addGrade(87);
        student2.addGrade(63);
        student2.addGrade(45);
        student2.addGrade(91);

        int student1GPA = student1.getGPA();
        int student2GPA = student2.getGPA();

        student1.printStudent();
        student2.printStudent();

        if (student1.compareGPA(student2) == 1) {
            System.out.println("Student 1 has a higher GPA");
        }
        else if (student1.compareGPA(student2) == 0){
            System.out.println("Two students have the same GPA");
        }
        else{
            System.out.println("Student 2 has a higher GPA");
        }
    }
}
