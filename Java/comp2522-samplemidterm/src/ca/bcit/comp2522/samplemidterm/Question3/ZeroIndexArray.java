package ca.bcit.comp2522.samplemidterm.Question3;

import java.util.ArrayList;

public class ZeroIndexArray {
    private ArrayList<Integer> items = null;

    public ZeroIndexArray() {
        items = new ArrayList<>();
    }

    public ArrayList<Integer> preIndex(Integer index) {
        ArrayList<Integer> tmpList = new ArrayList<Integer>();
        for (int currentIndex = 0; currentIndex <= index; currentIndex++) {
            tmpList.add(items.get(currentIndex));
        }
        return tmpList;
    }

    public ArrayList<Integer> postIndex(Integer index) {
        ArrayList<Integer> tmpList = new ArrayList<Integer>();
        for (int currentIndex = index + 1;
             currentIndex <= items.size(); currentIndex++) {
            tmpList.add(items.get(currentIndex));
        }
        return tmpList;
    }

    public int maxArray(int []intArray){
        int maxNumber = Integer.MIN_VALUE;
        for (int number: intArray){
            if (maxNumber < number){
                maxNumber = number;
            }
        }
        return maxNumber;
    }
}
