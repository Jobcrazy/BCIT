import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Algorithm {
    private static final int N = 100;
    private static final int MAX = 10;
    private static final int NUM_TRIALS = 1000000;

    private static class Result {
        public BigInteger comparison = BigInteger.valueOf(0);
        public BigInteger found = BigInteger.valueOf(0);
    }

    private static int generate_random_number(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static ArrayList<Integer> generate_random_number_list(
            int size, int min, int max) {
        ArrayList<Integer> number_list = new ArrayList<>();
        for (int index = 0; index < size; ++index) {
            number_list.add(generate_random_number(min, max));
        }
        return number_list;
    }

    private static Result sequential_search(
            ArrayList<Integer> numbers, int key) {
        Result result = new Result();
        for (Integer number : numbers) {
            result.comparison = result.comparison.add(BigInteger.valueOf(1));
            if (key == number) {
                result.found = result.found.add(BigInteger.valueOf(1));
                return result;
            }
        }
        return result;
    }

    private static int binary_search(
            int[] A, int searchKey, int startpt, int endpt, Result result) {
        result.comparison = result.comparison.add(BigInteger.valueOf(1));
        if (endpt < startpt) {
            return -1;
        }
        int midpt = (int) Math.floor((startpt + endpt) / 2.0);
        if (A[midpt] < searchKey) {
            return binary_search(A, searchKey, midpt + 1, endpt, result);
        } else if (A[midpt] > searchKey) {
            return binary_search(A, searchKey, startpt, midpt - 1, result);
        } else {
            result.found = result.found.add(BigInteger.valueOf(1));
            return midpt;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = generate_random_number_list(N, 0, MAX);

        System.out.println("Sequential Search...");
        BigInteger sequential_search_found = BigInteger.valueOf(0);
        BigInteger sequential_search_comparison = BigInteger.valueOf(0);
        for (int n = 0; n < NUM_TRIALS; ++n) {
            int X = generate_random_number(0, MAX);
            Result result = sequential_search(A, X);
            sequential_search_comparison =
                    sequential_search_comparison.add(result.comparison);
            sequential_search_found = sequential_search_found.add(result.found);
        }

        System.out.println("A. Trials: " + NUM_TRIALS);
        System.out.println("B. Comparisons: " + sequential_search_comparison);
        System.out.println("C. Found: " + sequential_search_found);
        System.out.println("D. Average comparisons: "
                + sequential_search_comparison.divide(
                BigInteger.valueOf(NUM_TRIALS)));
        System.out.println("E. Success rate: " +
                sequential_search_found.floatValue() /
                sequential_search_comparison.floatValue());
        System.out.println("F. Mystery number: " + Math.floor(60 *
                sequential_search_found.floatValue() /
                sequential_search_comparison.floatValue()));


        System.out.println("Binary Search...");
        int[] int_array = A.stream().mapToInt(Integer::valueOf).toArray();
        merge_sort(int_array);

        BigInteger binary_search_found = BigInteger.valueOf(0);
        BigInteger binary_search_comparison = BigInteger.valueOf(0);
        for (int n = 0; n < NUM_TRIALS; ++n) {
            int X = generate_random_number(0, MAX);
            Result result = new Result();
            binary_search(int_array, X, 0, A.size() - 1, result);
            binary_search_comparison =
                    binary_search_comparison.add(result.comparison);
            binary_search_found = binary_search_found.add(result.found);
        }

        System.out.println("A. Trials: " + NUM_TRIALS);
        System.out.println("B. Comparisons: " + binary_search_comparison);
        System.out.println("C. Found: " + binary_search_found);
        System.out.println("D. Average comparisons: " +
                binary_search_comparison.floatValue() / NUM_TRIALS);
        System.out.println("E. Success rate: " +
                binary_search_found.floatValue() /
                binary_search_comparison.floatValue());
        System.out.println("F. Mystery number: " + Math.floor(60 *
                binary_search_found.floatValue() /
                binary_search_comparison.floatValue()));
    }

    private static void array_copy(
            int[] src,
            int src_start,
            int src_end,
            int[] destine,
            int destine_start) {
        // Copy src[src_start, src_end] to destine[destine_start, src_end - src_start]
        // src: an array that the elements will be copied from
        // src_start: the starting index in src that the elements will be copied from
        // src_end: the ending index in src that the elements will be copied from
        // destine: an array that the element will be copied to
        // destine_start : the starting index in destine that the elements will be copied to
        for (; src_start < src_end; src_start++, destine_start++) {
            destine[destine_start] = src[src_start];
        }
    }

    private static void merge(
            int[] destine,
            int[] first_half,
            int[] second_half) {
        // merge first_half and second_half to destine in asc order
        // destine: an array store the sorted elements from first_half and second_half
        // first_half: an sorted array
        // second_half: another sorted array
        int array_index = 0, first_index = 0, second_index = 0;

        while (first_index < first_half.length &&
                second_index < second_half.length) {
            if (first_half[first_index] < second_half[second_index]) {
                destine[array_index] = first_half[first_index];
                first_index++;
            } else {
                destine[array_index] = second_half[second_index];
                second_index++;
            }

            array_index++;
        }

        if (first_index == first_half.length) {
            array_copy(second_half, second_index,
                    second_half.length, destine, array_index);
        } else {
            array_copy(first_half, first_index,
                    first_half.length, destine, array_index);
        }
    }

    private static void merge_sort(int[] array) {
        // sort array with merge sort algorithm
        // array: the array to be sorted
        if (1 == array.length) {
            return;
        }

        int[] first_half = Arrays.copyOfRange(
                array, 0, array.length / 2);
        int[] second_half = Arrays.copyOfRange(
                array, array.length / 2, array.length);

        merge_sort(first_half);
        merge_sort(second_half);

        merge(array, first_half, second_half);
    }
}