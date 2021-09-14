package ca.bcit.comp2522.assignment7.question5;

import java.util.Arrays;

public class Question5 {
    public void test() {
        Integer[] intArray = {10, 12, 30, 40, 50, 16, 17, 18, 80, 100};
        Integer[] anotherIntArray = {12, 30, 40, 50};
        IntUnaryPredicate predicate =
                new IntUnaryPredicate(Arrays.asList(anotherIntArray));
        int result = Util.findFirst(Arrays.asList(intArray),
                0, intArray.length, predicate);

        System.out.println("The first prime is: " + intArray[result]);
    }
}
