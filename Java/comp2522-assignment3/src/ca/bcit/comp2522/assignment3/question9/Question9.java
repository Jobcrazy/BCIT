package ca.bcit.comp2522.assignment3.question9;

import ca.bcit.comp2522.assignment3.question1.Question1;

import java.util.ArrayList;

public class Question9 {
    public int primeVisibility(int[] numberList, int topIndex) {
        int result = 0;

        for (int index = 2; index < numberList.length && index <= topIndex; index++) {
            if (Question1.isPrime(index)){
                result++;
            }
        }

        return result;
    }

    public void test() {
        int[] numbers = {1, 4, 5, 6, 1, 2, 4, 5, 6, 5};
        System.out.println("Number list: {1, 4, 5, 6, 1, 2, 4, 5, 6, 5}");

        int maxIndex = 8;
        System.out.println("Max index: 8");

        System.out.println("There a "+ primeVisibility(numbers, maxIndex)
                + " numbers with prime visibility");
    }
}
