/*
 * COMP 3761, Summer 2021, Lab 6
 *
 * Student Name: Hang Liu
 * Student ID: A01173804
 *
 * This program tests different hashing algorithms
 */

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Algorithm {
    private interface HashFunction {
        // An interface for all hash functions to implement
        // key: a string that to be hashed
        // hashTableSize: the size of the hash table array
        int hash(String key, int hashTableSize);
    }

    private static int convertCharToInt(char singleChar) {
        // Convert a single character to an integer
        // For example: A(1), B(2), C(3), etc.
        // singleChar: the character to be converted
        return singleChar - 'A' + 1;
    }

    private final static HashFunction H1 = (key, hashTableSize) -> {
        // String hashing function (version 1)
        // key: a string that to be hashed
        // hashTableSize: the size of the hash table array
        int hash = 0;
        for (int index = 0; index < key.length(); ++index) {
            // Compute the sum of the values of the letters in the string
            hash += convertCharToInt(key.charAt(index));
        }

        // Divide the sum by the hash table size and return the remainder
        return hash % hashTableSize;
    };

    private final static HashFunction H2 = (key, hashTableSize) -> {
        // String hashing function (version 2)
        // key: a string that to be hashed
        // hashTableSize: the size of the hash table array
        BigInteger hash = BigInteger.valueOf(0);
        for (int index = 0; index < key.length(); ++index) {
            // For the ith letter in the string (counting from 0)
            // multiply the character value (A=1, B=2, C=3) times 26^i.
            hash = hash.add(
                    BigInteger.valueOf(convertCharToInt(key.charAt(index)))
                            .multiply(BigInteger.valueOf(26).pow(index))
            );
        }

        // Divide the sum by the hash table size and return the remainder
        return hash.mod(BigInteger.valueOf(hashTableSize)).intValue();
    };

    private final static HashFunction H3 = (key, hashTableSize) -> {
        // String hashing function (version 3)
        // key: a string that to be hashed
        // hashTableSize: the size of the hash table array

        // Init the hash code to 5381
        BigInteger hash = BigInteger.valueOf(5381);
        for (int index = 0; index < key.length(); ++index) {
            // Left shift the hash code for 5 bits, then add the previous hash
            // code and the current character
            hash = hash.shiftLeft(5).add(hash).add(
                    BigInteger.valueOf(convertCharToInt(key.charAt(index))));
        }

        //return hash % hashTableSize;
        return hash.mod(BigInteger.valueOf(hashTableSize)).intValue();
    };

    private static int getCollisions(
            String[] names,
            int hashTableSize,
            HashFunction hashFunction) {
        // Compute the number of the collisions with specific hashing function
        // names: a string array of student names
        // hashTableSize: the size of the hash table array
        // hashFunction: the function to generate the hash code
        int collisions = 0;

        int[] hashTable = new int[hashTableSize];
        for (String name : names) {
            // Compute the hash code of the string first
            int hashCode = hashFunction.hash(name, hashTableSize);
            if (0 != hashTable[hashCode]){
                // Add 1 if there is a collision
                collisions++;
            }

            while (0 != hashTable[hashCode]) {
                //Move to the next "empty bucket" if the current bucket is full
                ++hashCode;
                if (hashCode >= hashTableSize) {
                    hashCode = 0;
                }
            }
            hashTable[hashCode] = 1;
        }

        // Return the total collision number
        return collisions;
    }

    private static void processFile(int filePrefix) {
        // Process a file and print the collision results
        // filePrefix: the prefix of the file name
        String[] names;
        String fileName = filePrefix + "_names.txt";

        try {
            // Try to read file with the composited file name
            names = readNames(fileName);
        } catch (Exception e) {
            // If error occurs, then return
            System.out.println("File reading error: " + fileName);
            return;
        }

        System.out.println("Input File: " + fileName);

        // Generate the result with different hash table size
        int[] times = new int[]{1, 2, 5, 10};
        for (int time : times) {
            int hashTableSize = filePrefix * time;
            int h1Result =
                    getCollisions(names, hashTableSize, H1);
            int h2Result =
                    getCollisions(names, hashTableSize, H2);
            int h3Result =
                    getCollisions(names, hashTableSize, H3);

            // Print the results
            System.out.printf("Hash Table Size: %d ", hashTableSize);
            System.out.printf("Collisions: H1(%d) H2(%d) H3(%d)\n",
                    h1Result, h2Result, h3Result);
        }

        // Output a new line
        System.out.println();
    }

    public static void main(String[] args) {
        // The entry function
        int[] filePrefixes = new int[]{37, 333, 5163};
        for (int filePrefix : filePrefixes) {
            // Process the files one by one
            processFile(filePrefix);
        }
    }

    private static String[] readNames(String fileName) throws IOException {
        // Read the student names from the given file
        // fileName: a string of the file name
        String[] nameArray = null;

        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            nameArray = sc.nextLine().split(",");
        }

        sc.close();
        return nameArray;
    }
}