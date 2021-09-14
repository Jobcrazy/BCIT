package ca.bcit.comp2522.assignment8.question1;

public class Question1 {
    public void test(){
        System.out.println("---Question 1---");
        System.out.println("Class Test64 Output:");
        Test64.test();

        System.out.println("Inner Test64 Output:");
        Foo1 foo1 = new Foo1(100);
        Foo1.Test64 test64 = foo1.new Test64();
        test64.test();
    }
}
