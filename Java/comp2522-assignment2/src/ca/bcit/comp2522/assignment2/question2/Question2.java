package ca.bcit.comp2522.assignment2.question2;

import java.util.Scanner;

public class Question2 {
    public static String handleInput(){
        // Open a Scanner for input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input a number:");
            if ( scanner.hasNextInt() ){
                String value = scanner.next();
                scanner.close();
                return value;
            }
            scanner.next();
            System.out.println("Invalid number");
        }
    }

    public static void reverseNumber() {
        String result = "";
        String strNumber = handleInput();

        // Reverse the string
        for (int index = strNumber.length() - 1; index >= 0; index--) {
            result  += strNumber.charAt(index);
        }

        System.out.printf("Reversed Number: %s\n", result);
    }
}
