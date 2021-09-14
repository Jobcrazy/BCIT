package ca.bcit.comp2522.assignment7.question5;

import java.util.Collection;
import java.util.List;

public class IntUnaryPredicate implements UnaryPredicate<Integer> {
    private final Collection<Integer> collection;

    public IntUnaryPredicate(Collection<Integer> collection) {
        this.collection = collection;
    }

    public boolean test(Integer number) {
        for (Integer i : collection) {
            if (1 != GCD(number, i)){
                return false;
            }
        }

        return collection.size() > 0;
    }

    public static int GCD(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }
}
