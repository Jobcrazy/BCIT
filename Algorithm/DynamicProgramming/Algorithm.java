/*
 * COMP 3761, Summer 2021, Lab 9
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests dynamic programming algorithms
 */

import java.math.BigInteger;

public class Algorithm {
    private static void testRecursive(
            int maxNumber, boolean enableCache) {
        // Test the recursive method, it will take a long time to compute the
        // result up to maxNumber.
        if (enableCache) {
            System.out.println("-----SW_Recursive with cache-----");
        } else {
            System.out.println("-----SW_Recursive without cache-----");
        }

        BlockMap blockMap = new BlockMap(maxNumber, maxNumber, enableCache);

        for (int i = 0; i <= maxNumber; ++i) {
            long start = System.currentTimeMillis();
            BigInteger result = blockMap.SW_Recursive(i, i);
            long end = System.currentTimeMillis();
            System.out.printf("SW_Recursive(%d,%d) = %s, time is %d ms\n",
                    i, i, result, end - start);
        }
    }

    private static void testDynamicProg(int maxNumber) {
        // Test the dynamic programming method, it will take much shorter time
        // to compute the result up to maxNumber.
        System.out.println("-----SW_DynamicProg-----");

        BlockMap blockMap = new BlockMap(maxNumber, maxNumber, false);

        for (int i = 0; i <= maxNumber; ++i) {
            long start = System.currentTimeMillis();
            BigInteger result = blockMap.SW_DynamicProg(i, i);
            long end = System.currentTimeMillis();
            System.out.printf("SW_DynamicProg(%d,%d) = %s, time is %d ms\n",
                    i, i, result, end - start);
        }
    }

    public static void main(String[] args) {
        // Test the recursive method without cache, it will take a long time
        // to compute the result up to 15.
        testRecursive(15, false);

        // Test the recursive method with cache, it will take a much shorter
        // time to compute the result up to 37.
        testRecursive(37, true);

        // Test the dynamic programming method, it will take much shorter time
        // to compute the result up to 37.
        testDynamicProg(37);
    }
}