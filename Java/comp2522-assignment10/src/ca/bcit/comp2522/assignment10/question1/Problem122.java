package ca.bcit.comp2522.assignment10.question1;

import java.util.function.BiFunction;

public class Problem122 {
    static class Arithmetic {
        public static int add(int a, int b) {
            return a + b;
        }
    }

    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> adder =
                Problem122.Arithmetic::add;

        int result = adder.apply(10, 20);
        System.out.println(result);
    }
}
