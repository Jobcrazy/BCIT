package ca.bcit.comp2522.assignment7.question1;

import java.util.ArrayList;

public class Question1 {
    private final ArrayList<Student> students = new ArrayList<>();

    public void test() {
        Student student = new Student(1, "Harry Potter", 75.0f);
        students.add(student);

        student = new Student(2, "Dani Jin", 85.5f);
        students.add(student);

        student = new Student(3, "James Liu", 90.0f);
        students.add(student);

        Util util = new Util();

        Integer total = util.countNumberOfElementsWithSpecificProperty(students,
                stu -> stu.getScore() > 80);

        System.out.println("Number of Students whose score is greater than 85" +
                " is: " + total);
    }
}
