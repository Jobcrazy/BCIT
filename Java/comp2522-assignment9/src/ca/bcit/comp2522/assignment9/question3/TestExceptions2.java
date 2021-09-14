package ca.bcit.comp2522.assignment9.question3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestExceptions2 {
    public static class Container<T> {
        List<T> list;

        Container(List<T> list) {
            this.list = list;
        }

        List<T> getList() {
            return list;
        }
    }

    public static class PredicateException1 extends Exception {
        PredicateException1(String message) {
            super(message);
        }
    }

    public static class PredicateException2 extends Exception {
        PredicateException2(String message) {
            super(message);
        }
    }

    public static <T> int evaluateList(Container<T> container, Predicate<T>
            predicate1, Predicate<T> predicate2) throws Exception {
        int count = 0;
        for (T t : container.getList()) {
            if (predicate1.test(t)) {
                if (predicate2.test(t)) {
                    count++;
                } else {
                    throw new PredicateException2("PredicateException2 raised");
                }
            } else {
                throw new PredicateException1("PredicateException2 raised");
            }
        }
        return count;
    }

    public void test() {
        ArrayList<String> list =
                new ArrayList<>(Arrays.asList("Exceptions", "are", "not",
                         "fun", "kidding", "they", "are", "fun"));
        Container<String> container = new Container<>(list);

        try {
            TestExceptions2.evaluateList(container, s -> s.length() > 4,
                    s -> s.contains("a"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
