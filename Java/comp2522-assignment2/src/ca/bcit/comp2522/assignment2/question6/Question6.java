package ca.bcit.comp2522.assignment2.question6;

public class Question6 {
    public static boolean isValidStatement(String strStatement) {
        /*
         * Rules:
         * 1. The variable names contain only one alphabet
         * 2. Number of open and close parenthesises in the statement is the same
         * 3. At any point (character or index) of the statement, number of the open
         *    parenthesises is bigger than or equal the number of close parenthesises.
         */

        int parenthesises = 0;

        char firstChar = strStatement.charAt(0);
        if (!checkAlphabet(firstChar) && firstChar != '(' && firstChar != ')') {
            // the first character must be a-z or '(' or ')'
            return false;
        }

        for (int index = 0; index < strStatement.length(); index++) {
            char nextChar = 0;
            boolean hasNextChar = false;
            if (index != strStatement.length() - 1) {
                hasNextChar = true;
                nextChar = strStatement.charAt(index + 1);
            }

            if (strStatement.charAt(index) == '(') {
                parenthesises++;
                // there must be an alphabet or '(' on the right side of a '('
                if (hasNextChar && !checkAlphabet(nextChar) && nextChar != '(') {
                    return false;
                }
            } else if (strStatement.charAt(index) == ')') {
                parenthesises--;
                // there must be an operator or ')' on the right side of a ')'
                if (hasNextChar && !checkOperator(nextChar) && nextChar != ')') {
                    return false;
                }
            } else if (checkAlphabet(strStatement.charAt(index))) {
                // there must be an operator or ')' on the right side of an alphabet
                boolean x = !checkOperator(nextChar);
                boolean y = nextChar != ')';
                if (hasNextChar && !checkOperator(nextChar) && nextChar != ')') {
                    return false;
                }
            } else if (checkOperator(strStatement.charAt(index))) {
                // there must be an alphabet or '(' on the right side of an operator
                if (hasNextChar && !checkAlphabet(nextChar) && nextChar != '(') {
                    return false;
                }

                // the last character must not be an operator
                if (!hasNextChar) {
                    return false;
                }
            }
        }

        return 0 == parenthesises;
    }

    public static boolean checkAlphabet(char variable) {
        return !(variable < 'a' || variable > 'z');
    }

    public static boolean checkOperator(char operator) {
        return operator == '+' ||
                operator == '-' ||
                operator == '*' ||
                operator == '/' ||
                operator == '%';
    }

    public static void testStatement(){
        // a+b*a+c/c%y
        // (a+b)*(a/d-(a/b))

        String positiveResult = " is a valid statement.";
        String negativeResult = " is an invalid statement.";

        String statementA = "a+b*a+c/c%y";
        String statementB = "(a+b)*(a/d-(a/b))";
        String statementC = "a";
        String statementD = "a+b";
        String statementE = "a+b)";
        String statementF = "(a+b*(a/d-(a/b))";
        String statementG = "(a+b)*)(a/d-(a/b))";

        System.out.println( statementA + (isValidStatement(statementA) ? positiveResult : negativeResult));
        System.out.println( statementB + (isValidStatement(statementB) ? positiveResult : negativeResult));
        System.out.println( statementC + (isValidStatement(statementC) ? positiveResult : negativeResult));
        System.out.println( statementD + (isValidStatement(statementD) ? positiveResult : negativeResult));
        System.out.println( statementE + (isValidStatement(statementE) ? positiveResult : negativeResult));
        System.out.println( statementF + (isValidStatement(statementF) ? positiveResult : negativeResult));
        System.out.println( statementG + (isValidStatement(statementG) ? positiveResult : negativeResult));
    }
}
