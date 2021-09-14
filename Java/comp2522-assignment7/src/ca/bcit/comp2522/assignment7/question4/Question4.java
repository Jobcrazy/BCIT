package ca.bcit.comp2522.assignment7.question4;

import java.util.ArrayList;

public class Question4 {
    public void test() {
        GenericList<Integer> intList = new GenericList<>();

        intList.addItemToList(1);
        intList.addItemToList(2);
        intList.addItemToList(4);
        intList.addItemToList(7);
        intList.addItemToList(9);

        final ArrayList<Integer> results =
                intList.performOperation(number -> number > 5);

        for (Integer item : results) {
            System.out.printf("%d ", item);
        }

        System.out.println();
    }
}
