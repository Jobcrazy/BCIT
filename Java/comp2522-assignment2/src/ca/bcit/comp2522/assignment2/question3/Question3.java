package ca.bcit.comp2522.assignment2.question3;

import java.util.Scanner;

public class Question3 {
    public static String handleInput() {
        // Open a Scanner for input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input a number:");
            if (scanner.hasNextInt()) {
                String value = scanner.next();
                scanner.close();
                return value;
            }
            scanner.next();
            System.out.println("Invalid number");
        }
    }

    public static String convertToBinary(int number) {
        String result = "";
        while (true) {
            int quotient = number / 2;
            int remainder = number % 2;
            number = quotient;
            result = "" + remainder + result;

            if (quotient == 0) {
                return result;
            }
        }
    }

    public static void toBinary() {
        int number = Integer.parseInt(handleInput());
        System.out.printf("Converted Binary: %s\n", convertToBinary(number));
    }
}