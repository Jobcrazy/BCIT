package ca.bcit.comp2522.assignment4.question1;

import java.util.ArrayList;

public class Student {
    private int studentID;
    private final String studentName;
    private final ArrayList<Integer> studentGradeList;

    public Student(int id, String name, ArrayList<Integer> grades) {
        studentID = id;
        studentName = name;
        studentGradeList = grades;
    }

    public Student(int id, String name) {
        studentID = id;
        studentName = name;
        studentGradeList = new ArrayList<>();
    }

    public boolean addGrade(Integer grade) {
        if (null == studentGradeList) {
            return false;
        }

        studentGradeList.add(grade);
        return true;
    }

    public int getGPA() {
        Integer total = 0;
        Integer numberOfCourses = 0;

        for (Integer grade : studentGradeList) {
            total += grade;
            numberOfCourses++;
        }

        return total / numberOfCourses;
    }

    public void printStudent() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Student Name: " + studentName);
        System.out.println("Student's GPA: " + getGPA());

        System.out.println("Student's Grades:");
        for (Integer grade : studentGradeList) {
            System.out.println(grade.toString());
        }
    }

    public int compareGPA(Student otherStudent) {
        int myGPA = getGPA();
        int otherGPA = otherStudent.getGPA();

        if (myGPA > otherGPA) {
            return 1;
        } else if (myGPA == otherGPA) {
            return 0;
        } else {
            return -1;
        }
    }
}
