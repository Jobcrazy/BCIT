package ca.bcit.comp2522.assignment4.question2.problem5;

public class Statement {
    private final int OPERATOR_UNKNOWN = 0;
    private final int OPERATOR_ADDITION = 1;
    private final int OPERATOR_SUBTRACTION = 2;
    private final int OPERATOR_MULTIPLICATION = 3;
    private final int OPERATOR_DIVISION = 4;

    private final int STEP_FIRST_NUMBER = 1;
    private final int STEP_SECOND_NUMBER = 2;
    private final int STEP_OPERATOR = 3;

    private final char CHAR_ADDITION = '+';
    private final char CHAR_SUBTRACTION = '-';
    private final char CHAR_DIVISION = '/';
    private final char CHAR_MULTIPLICATION = '*';

    private int firstNumber;
    private int secondNumber;
    private int operatorType = OPERATOR_UNKNOWN;

    public boolean parse(String statement) {
        String tmpNumber = "";
        int currentStep = STEP_FIRST_NUMBER;

        for (int index = 0; index < statement.length(); index++) {
            char currentChar = statement.charAt(index);
            if (STEP_FIRST_NUMBER == currentStep) {
                if (Character.isDigit(currentChar)) {
                    tmpNumber += currentChar;
                } else {
                    firstNumber = Integer.parseInt(tmpNumber);
                    tmpNumber = "";
                    currentStep = STEP_OPERATOR;
                    index--;
                }
            } else if (STEP_OPERATOR == currentStep) {
                if (OPERATOR_UNKNOWN != operatorType) {
                    return false;
                }

                if (CHAR_ADDITION == currentChar) {
                    operatorType = OPERATOR_ADDITION;
                } else if (CHAR_SUBTRACTION == currentChar) {
                    operatorType = OPERATOR_SUBTRACTION;
                } else if (CHAR_MULTIPLICATION == currentChar) {
                    operatorType = OPERATOR_MULTIPLICATION;
                } else if (CHAR_DIVISION == currentChar) {
                    operatorType = OPERATOR_DIVISION;
                } else {
                    return false;
                }

                currentStep = STEP_SECOND_NUMBER;
            } else if (STEP_SECOND_NUMBER == currentStep) {
                if (Character.isDigit(currentChar)) {
                    tmpNumber += currentChar;
                } else {
                    return false;
                }
            }
        }
        secondNumber = Integer.parseInt(tmpNumber);
        return true;
    }

    public int performOperation() {
        switch (operatorType) {
            case OPERATOR_ADDITION:
                return firstNumber + secondNumber;
            case OPERATOR_SUBTRACTION:
                return firstNumber - secondNumber;
            case OPERATOR_MULTIPLICATION:
                return firstNumber * secondNumber;
            case OPERATOR_DIVISION:
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }
}
