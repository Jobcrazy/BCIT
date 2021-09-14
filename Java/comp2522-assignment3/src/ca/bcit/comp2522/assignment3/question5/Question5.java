package ca.bcit.comp2522.assignment3.question5;

import ca.bcit.comp2522.assignment3.question4.Question4;

import java.util.ArrayList;

public class Question5 {
    private Question4 q4 = new Question4();

    public void printRepeatedTimes(int[] numberList) {
        if (0 == numberList.length) {
            return;
        }

        for (int index = 0; index < numberList.length; index++) {
            q4.addNumber(numberList[index]);
        }

        for (int index = 0; index < q4.counters.size(); index++) {
            System.out.printf("%d: %d times\n",
                    q4.numbers.get(index), q4.counters.get(index));
        }
    }

    public void test() {
        int[] numbers = {1, 4, 5, 6, 1, 2, 4, 5, 6, 5};
        System.out.println("Number list: {1, 4, 5, 6, 1, 2, 4, 5, 6, 5}");
        printRepeatedTimes(numbers);
    }
}
