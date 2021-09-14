package ca.bcit.comp2522.assignment10.question3;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Question3 {
    public void test() {
        Supplier<Stream<Student>> studentStream =
                () -> Stream.of(new Student("James",
                                new double[]{80, 90, 95, 100}),
                        new Student("Dani",
                                new double[]{90, 92, 95, 100}),
                        new Student("Vincent",
                                new double[]{88, 94, 85, 95}),
                        new Student("Newman",
                                new double[]{60, 70, 65, 70}));

        System.out.println("-----Question 3.1-----");
        studentStream.get().collect(
                Collectors.toMap(
                        s -> s.name,
                        s -> DoubleStream.of(s.grades).average()))
                .forEach((k, v) -> {
                    if (v.isPresent())
                        System.out.println(v.getAsDouble());
                });

        System.out.println("-----Question 3.2-----");
        Student stu = studentStream.get()
                .reduce(new Student("", new double[]{0,0}), (result, student)->{
                    for(double grade: student.grades){
                        result.grades[0] += grade;
                    }
                    result.grades[1] += student.grades.length;
                    return result;
                });
        System.out.printf("All student's GPA is: %.2f\n", stu.grades[0]/stu.grades[1]);

        System.out.println("-----Question 3.3-----");
        studentStream.get()
                .collect(
                        Collectors.toMap(
                                s -> s.name,
                                s -> DoubleStream.of(s.grades).average()))
                .forEach((k, v) -> {
                    if (v.isPresent() && v.getAsDouble() > 70)
                        System.out.println(v.getAsDouble());
                });

        System.out.println("-----Question 3.4-----");
        studentStream.get()
                .max((s1, s2) -> {
                    OptionalDouble s1gpa = DoubleStream.of(s1.grades).average();
                    OptionalDouble s2gpa = DoubleStream.of(s2.grades).average();
                    if (s1gpa.isPresent() && s2gpa.isPresent()) {
                        if (s1gpa.getAsDouble() > s2gpa.getAsDouble()) {
                            return 1;
                        } else if (s1gpa.getAsDouble() < s2gpa.getAsDouble()) {
                            return -1;
                        }
                    }
                    return 0;
                })
                .ifPresent(s -> System.out.println(s.name));

        System.out.println("-----Question 3.5-----");
        studentStream.get()
                .max((s1, s2) -> {
                    OptionalDouble s1gpa = DoubleStream.of(s1.grades).average();
                    OptionalDouble s2gpa = DoubleStream.of(s2.grades).average();
                    if (s1gpa.isPresent() && s2gpa.isPresent()) {
                        if (s1gpa.getAsDouble() > s2gpa.getAsDouble()) {
                            return 1;
                        } else if (s1gpa.getAsDouble() < s2gpa.getAsDouble()) {
                            return -1;
                        }
                    }
                    return 0;
                })
                .ifPresent(s -> {
                    OptionalDouble gpa = DoubleStream.of(s.grades).average();
                    if (gpa.isPresent()) {
                        System.out.println(s.name + " : " + gpa.getAsDouble());
                    }
                });
    }
}
