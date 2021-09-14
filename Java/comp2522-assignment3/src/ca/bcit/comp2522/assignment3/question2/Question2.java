package ca.bcit.comp2522.assignment3.question2;

import ca.bcit.comp2522.assignment3.question1.Question1;

public class Question2 {
    public static int findNextPrime(int primeNumber) {
        int result = primeNumber + 1;
        while ( !Question1.isPrime(result) ){
            result++;
        }
        return result;
    }

    public static void test(){
        int result = findNextPrime(7);
        System.out.println("The prime that is larger and next to 7 is: " + result);

        result = findNextPrime(11);
        System.out.println("The prime that is larger and next to 11 is: " + result);
    }
}
