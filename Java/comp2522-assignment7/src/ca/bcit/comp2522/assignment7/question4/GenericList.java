package ca.bcit.comp2522.assignment7.question4;

import java.util.ArrayList;
import java.util.function.Predicate;

public class GenericList<T> {
    private final ArrayList<T> list = new ArrayList<>();

    public void addItemToList(T item) {
        list.add(item);
    }

    void removeItemFromTheList(int index) {
        list.remove(index);
    }

    final ArrayList<T> performOperation(Predicate<T> condition) {
        ArrayList<T> results = new ArrayList<>();
        for (T item : list) {
            if (condition.test(item)) {
                results.add(item);
            }
        }
        return results;
    }
}
