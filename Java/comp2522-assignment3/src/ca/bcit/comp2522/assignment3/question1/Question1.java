package ca.bcit.comp2522.assignment3.question1;

public class Question1 {
    public static boolean isPrime(int positiveNumber) {
        for (int number = 2; number < positiveNumber; number++) {
            if (0 == positiveNumber % number) {
                return false;
            }
        }
        return true;
    }

    public static void test() {
        if (isPrime(7)) {
            System.out.println("7 is a prime!");
        } else {
            System.out.println("7 is not a prime!");
        }

        if (isPrime(9)) {
            System.out.println("9 is a prime!");
        } else {
            System.out.println("9 is not a prime!");
        }
    }
}
