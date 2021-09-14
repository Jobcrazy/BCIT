package ca.bcit.comp2522.assignment7.question3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question3 {
    public void test() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        ArrayList<Integer> intArrayList = new ArrayList<>(Arrays.asList(intArray));

        for (Integer item: intArrayList){
            System.out.printf("%d ", item);
        }

        System.out.println("");

        Util util = new Util();
        util.exchangeElements(intArrayList, 1, 3);

        for (Integer item: intArrayList){
            System.out.printf("%d ", item);
        }

        System.out.println("");
    }
}
