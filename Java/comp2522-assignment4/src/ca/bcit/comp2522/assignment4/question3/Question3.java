package ca.bcit.comp2522.assignment4.question3;

import java.util.ArrayList;

public class Question3 {
    public boolean checkSingleFactorEquality(String statementA, String statementB) {
        PolynomialEquation peOne = new PolynomialEquation();
        PolynomialEquation peTwo = new PolynomialEquation();

        peOne.parse(statementA);
        peTwo.parse(statementB);

        return peOne.equals(peTwo);
    }

    public boolean checkPolynomialEquationsEquality(String statementA, String statementB) {
        return checkSingleFactorEquality(statementA, statementB);
    }

    public int getMaxExponent(String statement) {
        int maxExponent = 0;

        PolynomialEquation pe = new PolynomialEquation();
        pe.parse(statement);

        ArrayList<EquationAttributes> attributeList = pe.getAttributes();

        for (int index = 0; index < attributeList.size(); index++) {
            int currentExponent = attributeList.get(index).exponent;
            if (index == 0 || maxExponent < currentExponent) {
                maxExponent = currentExponent;
            }
        }

        return maxExponent;
    }

    public String addPolynomialEquations(String statementA, String statementB) {
        String result = "";

        PolynomialEquation peOne = new PolynomialEquation();
        PolynomialEquation peTwo = new PolynomialEquation();

        peOne.parse(statementA);
        peTwo.parse(statementB);

        ArrayList<EquationAttributes> attributeList = peOne.add(peTwo);

        for (int index = 0; index < attributeList.size(); index++) {
            if (attributeList.get(index).coefficient > 0) {
                if (index != 0) {
                    result += "+";
                }
            } else {
                result += "-";
            }
            result += "" + attributeList.get(index).coefficient + "*x^" + attributeList.get(index).exponent;
        }

        return result;
    }

    public void test() {
        // Question 1:
        if (checkSingleFactorEquality("-x", "-1*x^0")) {
            System.out.println("-x and -1*x^0 are the same");
        } else {
            System.out.println("-x and -1*x^0 are not the same");
        }

        if (checkSingleFactorEquality("x", "-1*x^0")) {
            System.out.println("x and -1*x^0 are the same");
        } else {
            System.out.println("x and -1*x^0 are not the same");
        }

        // Question 2:
        if (checkSingleFactorEquality("5*x^4–3*x^2+6", "5*x^4–3*x^2+6*x^0")) {
            System.out.println("5*x^4–3*x^2+6 and 5*x^4–3*x^2+6*x^0 are the same");
        } else {
            System.out.println("5*x^4–3*x^2+6 and 5*x^4–3*x^2+6*x^0 are not the same");
        }

        if (checkSingleFactorEquality("5*x^4–3*x^2+6", "6+5*x^4–3*x^2")) {
            System.out.println("5*x^4–3*x^2+6 and 6+5*x^4–3*x^2 are the same");
        } else {
            System.out.println("5*x^4–3*x^2+6 and 6+5*x^4–3*x^2 are not the same");
        }

        // Question 3
        int maxExponent = getMaxExponent("10*x^5+8*x^4+x^2+6");
        System.out.println("The biggest exponents of 10*x^5+8*x^4+x^2+6 is " + maxExponent);

        // Question 4
        String addResultA = addPolynomialEquations("10*x^5", "5*x^5");
        System.out.println("10*x^5 plus 5*x^5 is " + addResultA);

        String addResultB = addPolynomialEquations("10*x^6", "5*x^5");
        System.out.println("10*x^6 plus 5*x^5 is " + addResultB);
    }
}
