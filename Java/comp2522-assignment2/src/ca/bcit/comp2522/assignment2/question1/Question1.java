package ca.bcit.comp2522.assignment2.question1;

import java.util.Scanner;

public class Question1 {
    public static void divisible() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a number:");
        int numberOne = scanner.nextInt();

        System.out.print("Input another number:");
        int numberTwo = scanner.nextInt();

        scanner.close();

        if (numberOne > numberTwo) {
            System.out.println("The second number should be greater than the first one.");
            return;
        }

        System.out.println("Numbers that can be divided by both 3 and 5");
        for (int number = numberOne + 1; number < numberTwo; number++) {
            if (0 == number % 3 && 0 == number % 5) {
                System.out.printf("%d\n", number);
            }
        }

        System.out.println("Numbers that can be divided by either 6 or 7");
        for (int number = numberOne; number < numberTwo; number++) {
            if (0 == number % 6 || 0 == number % 7) {
                System.out.printf("%d\n", number);
            }
        }

        System.out.println("Numbers that can only be divided by 3");
        for (int number = numberOne; number <= numberTwo; number++) {
            if (0 == number % 3) {
                System.out.printf("%d\n", number);
            }
        }
    }
}
