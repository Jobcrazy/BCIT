package ca.bcit.comp2522.assignment3.question10;

import java.util.ArrayList;

public class PolynomialEquation {
    private final int STEP_COEFFICIENT = 1;
    private final int STEP_BASE_FACTOR = 2;
    private final int STEP_EXPONENT = 3;

    private ArrayList<EquationAttributes> listAttributes = new ArrayList<>();

    private EquationAttributes tokenize(String equation) {
        EquationAttributes attributes = new EquationAttributes();
        int currentStep = STEP_COEFFICIENT;
        String strTemp = "";

        if (('-' == equation.charAt(0) ||
                '+' == equation.charAt(0)) &&
                'x' == equation.charAt(1)) {
            attributes.coefficient = '+' == equation.charAt(0) ? 1 : -1;
            equation = equation.substring(1);
        }

        for (int index = 0; index < equation.length(); index++) {
            char currentChar = equation.charAt(index);
            if (currentStep == STEP_COEFFICIENT) {
                if ((0 == index && ('+' == currentChar || '-' == currentChar)) ||
                        Character.isDigit(currentChar)) {
                    strTemp += currentChar;
                    if (index == equation.length() - 1) {
                        attributes.coefficient = Integer.parseInt(strTemp);
                        return attributes;
                    }
                } else if (0 == index && 'x' == currentChar) {
                    currentStep = STEP_EXPONENT;
                    if (index == equation.length() - 1) {
                        return attributes;
                    }
                } else if (currentChar == '*' || index == equation.length() - 1) {
                    currentStep = STEP_BASE_FACTOR;
                    attributes.coefficient = Integer.parseInt(strTemp);
                    strTemp = "";
                    if (index == equation.length() - 1) {
                        return attributes;
                    }
                }
            } else if (currentStep == STEP_BASE_FACTOR) {
                if (currentChar == '^') {
                    currentStep = STEP_EXPONENT;
                } else if (index == equation.length() - 1) {
                    return attributes;
                }
            } else if (currentStep == STEP_EXPONENT) {
                if (Character.isDigit(currentChar)) {
                    strTemp += currentChar;
                }

                if (index == equation.length() - 1) {
                    attributes.exponent = Integer.parseInt(strTemp);
                    return attributes;
                }
            }
        }

        return null;
    }

    public boolean parse(String statement) {
        String equation = "";
        EquationAttributes attributes = null;
        for (int index = 0; index < statement.length(); index++) {
            equation += statement.charAt(index);
            if (index == statement.length() - 1) {
                attributes = tokenize(equation);
                if (null == attributes) {
                    return false;
                }
                listAttributes.add(attributes);
            } else if (statement.charAt(index + 1) == '+' ||
                    statement.charAt(index + 1) == '-'
            ) {
                attributes = tokenize(equation);
                if (null == attributes) {
                    return false;
                }
                listAttributes.add(attributes);
                equation = "";
            }
        }
        return true;
    }

    public void reset() {
        listAttributes.clear();
    }

    public ArrayList<EquationAttributes> getAttributes() {
        return listAttributes;
    }

    public boolean equals(PolynomialEquation pe) {
        ArrayList<EquationAttributes> listAttributesB = pe.getAttributes();

        if (listAttributes.size() != listAttributesB.size()) {
            return false;
        }

        for (int index = 0; index < listAttributes.size(); index++) {
            if (listAttributes.get(index).coefficient != listAttributesB.get(index).coefficient ||
                    listAttributes.get(index).exponent != listAttributesB.get(index).exponent) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<EquationAttributes> add(PolynomialEquation pe) {
        //only two single equations can be added
        ArrayList<EquationAttributes> listAttributesB = pe.getAttributes();

        EquationAttributes attrA = listAttributes.get(0);
        EquationAttributes attrB = listAttributesB.get(0);

        if (attrA.exponent == attrB.exponent) {
            attrA.coefficient += attrB.coefficient;
        } else {
            listAttributes.add(attrB);
        }

        return listAttributes;
    }
}
