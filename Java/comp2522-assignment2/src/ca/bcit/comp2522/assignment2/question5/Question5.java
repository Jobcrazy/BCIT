package ca.bcit.comp2522.assignment2.question5;

import java.util.Scanner;

public class Question5 {
    public static void reverseString() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a string:");
        String strToReverse = scanner.next();

        scanner.close();

        StringBuilder strReversed = new StringBuilder();
        int length = strToReverse.length();
        for (int index = length - 1; index >= 0; index--) {
            strReversed.append(strToReverse.charAt(index));
        }

        System.out.printf("Reversed String: %s\n", strReversed.toString());

        if (strReversed.toString().equals(strToReverse)) {
            System.out.println("Two Strings are the same.");
        } else {
            System.out.println("Two Strings are not the same.");
        }
    }
}
