package ca.bcit.comp2522.assignment3.question4;

import java.util.ArrayList;

public class Question4 {
    public final ArrayList<Integer> counters = new ArrayList<>();
    public final ArrayList<Integer> numbers = new ArrayList<>();

    private final int NUMBER_NOT_EXIST = -1;

    private int getNumberIndex(int number) {
        for (int index = 0; index < numbers.size(); index++) {
            if (number == numbers.get(index)) {
                return index;
            }
        }
        return NUMBER_NOT_EXIST;
    }

    public void addNumber(int number) {
        int index = getNumberIndex(number);
        if (NUMBER_NOT_EXIST == index) {
            numbers.add(number);
            counters.add(1);
            return;
        }

        counters.set(index, counters.get(index) + 1);
    }

    public int findMostRepeated(int[] numberList) {
        if (0 == numberList.length) {
            return NUMBER_NOT_EXIST;
        }

        int mostRepeatedIndex = 0;

        for (int index = 0; index < numberList.length; index++) {
            addNumber(numberList[index]);
        }

        for (int index = 0; index < counters.size(); index++) {
            if (counters.get(mostRepeatedIndex) < counters.get(index)) {
                mostRepeatedIndex = index;
            }
        }

        return numbers.get(mostRepeatedIndex);
    }

    public void test() {
        int[] numbers = {1, 4, 5, 6, 1, 2, 4, 5, 6, 5};
        System.out.println("Number list: {1, 4, 5, 6, 1, 2, 4, 5, 6, 5}");
        System.out.printf("Most Repeated Number is: %d", findMostRepeated(numbers));
    }
}
