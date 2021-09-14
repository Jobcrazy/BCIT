package ca.bcit.comp2522.assignment2.question10;

import java.util.ArrayList;

public class Question10 {
    public static void findNumber() {
        int result;

        for (int number = 3; ; number++) {
            if (f1(number) > f2(number)) {
                result = number;
                break;
            }
        }

        System.out.println("T is: " + result);
    }

    public static int f1(int x) {
        return (int) Math.pow(2, x);
    }

    public static int f2(int x) {
        return (int) Math.pow(x, 5);
    }
}
