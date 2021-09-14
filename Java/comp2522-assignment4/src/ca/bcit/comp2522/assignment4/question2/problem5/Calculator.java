package ca.bcit.comp2522.assignment4.question2.problem5;

public class Calculator {
    int calculate(String strStatement){
        Statement statement = new Statement();
        if (!statement.parse(strStatement)){
            return 0;
        }
        return statement.performOperation();
    }
}
