package ca.bcit.comp2522.assignment9.question4;

import java.util.ArrayList;

public class Question4 {
    private BinarySearchTree<Student>
    createBinarySearchTree(ArrayList<Student> students) {
        BinarySearchTree<Student> treeStudent = new BinarySearchTree<>();

        for (Student student : students) {
            treeStudent.addNode(student);
        }
        return treeStudent;
    }

    private Boolean searchBST(BinarySearchTree<Student> students,
                              Student student) {

        return null != students.search(student);
    }

    public void test() {
        System.out.println("-----Question 4-----");

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(100, "Hang", "Liu"));
        students.add(new Student(50, "Dani", "Jin"));
        students.add(new Student(30, "Vincent", "Wong"));
        students.add(new Student(60, "Joon", "Kim"));
        students.add(new Student(200, "Sam", "Lew"));

        BinarySearchTree<Student> treeStudent =
                createBinarySearchTree(students);

        if (searchBST(treeStudent,
                new Student(60, "Joon", "Kim"))) {
            System.out.println("Student Found: Joon");
        } else {
            System.out.println("Student Not Found: Joon");
        }

        if (searchBST(treeStudent,
                new Student(601, "Mori", "Lee"))) {
            System.out.println("Student Found: Mori");
        } else {
            System.out.println("Student Not Found: Mori");
        }
    }
}
