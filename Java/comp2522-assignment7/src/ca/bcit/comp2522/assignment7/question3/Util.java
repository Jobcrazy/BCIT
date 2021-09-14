package ca.bcit.comp2522.assignment7.question3;

import java.util.List;

public class Util {
    public <E> void exchangeElements(List<E> list, int positionOne, int positionTwo) {
        E elementOne = list.get(positionOne);
        E elementTwo = list.get(positionTwo);

        if (null == elementOne || null == elementTwo) {
            return;
        }

        list.set(positionOne, elementTwo);
        list.set(positionTwo, elementOne);
    }
}
