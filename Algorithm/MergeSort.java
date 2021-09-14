/*
 * COMP 3761, Summer 2021, Lab 4
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program finds the smallest missing number from an int array
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {
    private static void array_copy(
            Integer[] src,
            int src_start,
            int src_end,
            Integer[] destine,
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
            Integer[] destine,
            Integer[] first_half,
            Integer[] second_half) {
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

    private static void merge_sort(Integer[] array) {
        // sort array with merge sort algorithm
        // array: the array to be sorted
        if (1 == array.length) {
            return;
        }

        Integer[] first_half = Arrays.copyOfRange(
                array, 0, array.length / 2);
        Integer[] second_half = Arrays.copyOfRange(
                array, array.length / 2, array.length);

        merge_sort(first_half);
        merge_sort(second_half);

        merge(array, first_half, second_half);
    }

    private static int find_smallest_missing_number(Integer[] array) {
        // find the smallest missing number from an array
        // array: an array in which the smallest missing number will be found

        // -1 indicates no missing number was found
        int current_missing_number = -1;
        int first_half_missing_number = -1;
        int second_half_missing_number = -1;

        // basic case
        if (array.length == 1) {
            return current_missing_number;
        }

        // divide the array to 2 arrays
        Integer[] first_half = Arrays.copyOfRange(
                array, 0, array.length / 2);
        Integer[] second_half = Arrays.copyOfRange(
                array, array.length / 2, array.length);

        // if the two parts are not continuous, the assign the current_missing_number
        if (first_half[first_half.length - 1] + 1 != second_half[0]) {
            current_missing_number = first_half[first_half.length - 1] + 1;
        }

        // if "the number of elements in the first half part" doesn't equal to
        // "the biggest number minus the smallest number in the first part, then
        // there is a missing number in the first half part. Other wise the missing
        // number is in the second half part
        if (first_half[first_half.length - 1] - first_half[0] != first_half.length - 1) {
            first_half_missing_number = find_smallest_missing_number(first_half);
        } else {
            second_half_missing_number = find_smallest_missing_number(second_half);
        }

        // return the result. -1 indicates no missing number was found
        if (-1 != first_half_missing_number) {
            return first_half_missing_number;
        } else if (-1 != current_missing_number) {
            return current_missing_number;
        }
        return second_half_missing_number;
    }

    public static void main(String[] args) throws IOException {
        //
        // Set the input data file name here
        //
        String fn = "length_4_N_8589.txt";

        //
        // Call helper function to read the input file
        //
        Integer[] data = read_array(fn);

        //
        // We need to know the length of the "strings" in this data file (for output
        // purposes later), but we read and saved them as integers, so the best way to
        // determine this now is to loop over the list, find the largest item, and
        // assume that all of the items are as long as that one.
        //
        Integer max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        int length_of_passcodes = String.valueOf(max).length();
        System.out.println("length of passcodes is " + length_of_passcodes);

        //
        // Print out the list of input data, for no particular reason
        //
        for (int i = 0; i < data.length; i++) {
            //
            // Format the numbers with the correct number of leading zeroes as
            // needed according to the length of the passcodes being processed.
            //
            String fmt = "%0" + length_of_passcodes + "d";
            System.out.println(String.format(fmt, data[i]));
        }
        System.out.println(data.length + " data items in the file");

        //
        //---------------------------------------------------------------
        // PUT YOUR MAIN PROGRAM LOGIC HERE
        //  1) Call your function to sort the input list
        //  2) Call your function to find the smallest missing number
        //  3) Print out the smallest missing number
        //---------------------------------------------------------------
        //
        merge_sort(data);
        int passcode = find_smallest_missing_number(data);
        System.out.printf(
                "The passcode is %0" + length_of_passcodes + "d\n", passcode);
    }

    public static Integer[] read_array(String filename) throws IOException {
        //
        // Reads the input file given by "filename", assumed to contain a list of
        // integer numbers. Stores the numbers into an array and returns the
        // array.
        //
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        //
        // Read the items initially into an ArrayList (for dynamic growth)
        //
        ArrayList<Integer> input_list = new ArrayList<Integer>();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            input_list.add(n);
        }

        //
        // Copy the ArrayList to an Integer[] array of the proper size
        //
        Integer[] arr = new Integer[input_list.size()];
        arr = input_list.toArray(arr);
        return arr;
    }
}
