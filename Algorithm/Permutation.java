/*
 * COMP 3761, Summer 2021, Lab 2
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program generates different light proposal for Santa's elves
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Algorithm {
	// Generate all possiable light proposals for size = N
    public static ArrayList<String> Question1(int N) {
        if (N > 1) {
            // Generate light list with N-1
            ArrayList<String> lowerList = Question1(N - 1);

            // Init an empty list
            ArrayList<String> currentList = new ArrayList<>();
            for (String s : lowerList) {
                for (int index = 0; index <= 2; ++index) {
                    // Copy an element from the lowerList, append an new light
                    // after it, and insert to the currentList
                    currentList.add(s + index);
                }
            }
            return currentList;
        }

        // Return ["0", "1", "2"] if N == 1
        return new ArrayList<>(Arrays.asList("0", "1", "2"));
    }

	// Generate all discontinuous light proposals for size = N
    public static ArrayList<String> Question2(int N) {
        if (N > 1) {
            // Generate light list with N-1
            ArrayList<String> lowerList = Question2(N - 1);

            // Init an empty list
            ArrayList<String> currentList = new ArrayList<>();
            for (String s : lowerList) {
                for (int index = 0; index <= 2; ++index) {
                    if (s.substring(s.length() - 1).equals("" + index)) {
                        // Skip if the previous light is the same to the current
                        continue;
                    }

                    // Copy an element from the lowerList, append an new light
                    // after it, and insert to the currentList
                    currentList.add(s + index);
                }
            }
            return currentList;
        }

        // Return ["0", "1", "2"] if N == 1
        return new ArrayList<>(Arrays.asList("0", "1", "2"));
    }

    public static void main(String[] args) {
		// Generate all light proposals for size = 1 to 10
        System.out.println("Generating all patterns of a given length:");
        for (int index = 1; index <= 10; ++index) {
            System.out.println("Length " + index + " produces " +
                    Question1(index).size() + " patterns.");
        }

        System.out.println();

		// Generate all discontinuous light proposals for size = 1 to 10
        System.out.println("Generating patterns without double-digits:");
        for (int index = 1; index <= 10; ++index) {
            System.out.println("Length " + index + " produces " +
                    Question2(index).size() + " patterns.");
        }
    }
}
