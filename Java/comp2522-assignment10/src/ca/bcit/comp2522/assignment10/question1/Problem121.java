package ca.bcit.comp2522.assignment10.question1;

import java.util.function.BiFunction;

public class Problem121 {
    public static void main(String[] args) {

        //adder adds two numbers together
        BiFunction<Integer, Integer, Integer> adder = Integer::sum;

        int result = adder.apply(10, 20);
        System.out.println(result);
    }
}
