package ca.bcit.comp2522.assignment7.question1;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Util {
    public <E> Integer countNumberOfElementsWithSpecificProperty(
            ArrayList<E> list, Predicate<E> condition) {
        Integer total = 0;

        for (E item : list) {
            if (condition.test(item)) {
                ++total;
            }
        }

        return total;
    }
}
