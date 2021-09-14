package ca.bcit.comp2522.assignment3.question6;

import ca.bcit.comp2522.assignment3.question4.Question4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Question6 {
    private Question4 q4 = new Question4();

    public int[] removeRepeated(int[] numberList) {
        if (0 == numberList.length) {
            return new int[]{};
        }

        for (int index = 0; index < numberList.length; index++) {
            q4.addNumber(numberList[index]);
        }

        int[] result = new int[q4.numbers.size()];
        for (int index = 0; index < q4.numbers.size(); index++) {
            result[index] = q4.numbers.get(index);
        }

        return result;
    }

    public void test() {
        int[] numberList = {1, 4, 5, 6, 1, 2, 4, 5, 6, 5};
        System.out.println("Number list: {1, 4, 5, 6, 1, 2, 4, 5, 6, 5}");
        numberList = removeRepeated(numberList);

        System.out.print("Result: ");

        for (int index = 0; index < numberList.length; index++) {
            System.out.printf("%d ", numberList[index]);
        }

        System.out.print("\n");
    }
}
