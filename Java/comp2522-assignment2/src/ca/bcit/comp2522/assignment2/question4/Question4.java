package ca.bcit.comp2522.assignment2.question4;

import java.util.ArrayList;
import java.util.Scanner;

public class Question4 {
    public static void absDistance() {
        String strNumber;
        ArrayList<Integer> listNumber = new ArrayList<>();

        // Open a Scanner for input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input a number:");
            if (scanner.hasNextInt()) {
                listNumber.add(scanner.nextInt());
            } else {
                // Sort
                sort(listNumber);

                // Print max, min and distance
                System.out.printf("Min: %d, Max: %d, Distance: %d", listNumber.get(0), listNumber.get(listNumber.size() - 1),
                        listNumber.get(listNumber.size() - 1) - listNumber.get(0) - 1);

                scanner.next();
                return;
            }
        }
    }

    public static void sort(ArrayList<Integer> listNumber) {
        for (int indexOuter = 0; indexOuter < listNumber.size(); indexOuter++) {
            for (int indexInner = indexOuter + 1; indexInner < listNumber.size(); indexInner++) {
                if (listNumber.get(indexOuter) > listNumber.get(indexInner)) {
                    int tmpNumber = listNumber.get(indexOuter);
                    listNumber.set(indexOuter, listNumber.get(indexInner));
                    listNumber.set(indexInner, tmpNumber);
                }
            }
        }
    }
}