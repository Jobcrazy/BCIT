package ca.bcit.comp2522.assignment2.question9;

import java.util.Scanner;

public class Question9 {
    public static void convertName() {
        // Open a Scanner for input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input a name:");
            String name = scanner.nextLine();

            boolean isValidName = true;

            for (int index = 0; index < name.length(); index++) {
                if (!Character.isAlphabetic(name.charAt(index))) {
                    isValidName = false;
                }
            }

            if (!isValidName) {
                scanner.close();
                System.out.println("Invalid name!");
                return;
            }

            System.out.println(name.toUpperCase());
        }
    }
}
