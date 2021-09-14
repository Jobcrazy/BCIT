package ca.bcit.comp2522.assignment2.question7;

import java.util.Scanner;

public class Question7 {
    public static double handleInput() {
        // Open a Scanner for input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input a number:");
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.close();
                return value;
            }
            scanner.next();
            System.out.println("Invalid number");
        }
    }

    public static void computeCircleArea() {
        double length = handleInput();
        double radius = Math.sqrt(2 * Math.pow(length, 2));
        System.out.println("The area of the circle is: " + Math.PI * radius * radius);
    }
}
