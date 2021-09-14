package ca.bcit.comp2522.assignment8.question5;

import ca.bcit.comp2522.assignment8.question4.Test;

import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Question5 {
    private static class Student {
        Integer id;
        String name;
        Integer age;
        Integer gender;

        public Student(Integer id, String name, Integer age, Integer gender) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }

    public void printStudents(Student student, BiPredicate<Integer,
            Integer> condition) {
        if (condition.test(student.age, student.gender)) {
            System.out.printf("ID: %d: - Name: %s - Age: %d - Gender: %s\n",
                    student.id, student.name, student.age,
                    student.gender == 1 ? "Male" : "Female");
        }
    }

    public void test() {
        System.out.println("---Question 5---");

        // Problem 1: Use BiPredicate to print all male students whose age is
        // greater than 15 from an student arraylist
        System.out.println("Problem 1: print all male students whose age is " +
                "greater than 15 from an student arraylist");
        ArrayList<Student> studentList = new ArrayList<>();

        studentList.add(new Student(1, "James Liu", 37, 1));
        studentList.add(new Student(2, "Dani J", 12, 0));
        studentList.add(new Student(3, "Joon K", 20, 1));

        for (Student student : studentList) {
            printStudents(student, (age, gender) -> age > 15 && gender == 1);
        }

        // Problem 2: Use Consume interface to print all female students
        System.out.println("Problem 2: Use Consume interface to print all " +
                "female students");
        Consumer<Student> consumer = student -> {
            if (student.gender != 1) {
                System.out.printf("ID: %d: - Name: %s - Age: %d - Gender: %s\n",
                        student.id, student.name, student.age,
                        1 == student.gender ? "Male" : "Female");
            }
        };
        for (Student student : studentList) {
            consumer.accept(student);
        }

        // Problem 3: Use Function interface to return all male students
        System.out.println("Problem 3: Use Function interface to return all " +
                "male students");
        Function<ArrayList<Student>, ArrayList<Student>> stuFunction =
                students -> {
                    ArrayList<Student> result = new ArrayList<>();
                    for (Student student: students){
                        if(student.gender == 1){
                            result.add(student);
                        }
                    }
                    return result;
                };

        ArrayList<Student> mailStudents = stuFunction.apply(studentList);
        for (Student student : mailStudents) {
            System.out.printf("ID: %d: - Name: %s - Age: %d - Gender: %s\n",
                    student.id, student.name, student.age,
                    1 == student.gender ? "Male" : "Female");
        }
    }
}
