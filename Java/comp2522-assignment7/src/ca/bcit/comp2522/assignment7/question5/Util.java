package ca.bcit.comp2522.assignment7.question5;

import java.util.List;

public class Util {
    public static <T> int findFirst(
            List<T> list, int begin, int end, UnaryPredicate<T> p) {
        for (; begin < end; ++begin) {
            if (p.test(list.get(begin))) {
                return begin;
            }
        }
        return -1;
    }
}
