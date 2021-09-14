package ca.bcit.comp2522.assignment4.question2.problem5;

public class Problem5 {
    public void test(){
        Calculator calculator = new Calculator();

        System.out.println("15+45=" + calculator.calculate("15+45"));
        System.out.println("15-45=" + calculator.calculate("15-45"));
        System.out.println("15*45=" + calculator.calculate("15*45"));
        System.out.println("45/15=" + calculator.calculate("45/15"));
    }
}
