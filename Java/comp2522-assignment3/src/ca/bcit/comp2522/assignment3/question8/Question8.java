package ca.bcit.comp2522.assignment3.question8;

import java.util.ArrayList;

public class Question8 {
    public ArrayList<Integer> findDivisibleNumbers(int topLimit, int number) {
        ArrayList<Integer> numberList = new ArrayList<>();

        for (int currentNumber = 2; currentNumber <= topLimit; currentNumber++) {
            if (0 == currentNumber % number) {
                numberList.add(currentNumber);
            }
        }

        return numberList;
    }

    public void test(){
        System.out.println("topLimit: 60, number: 5");

        ArrayList<Integer> results = findDivisibleNumbers(60, 5);

        System.out.println("Results:");
        for (int index = 2; index < results.size(); index++) {
            System.out.printf("%d ", results.get(index));
        }

        System.out.println("");
    }
}
