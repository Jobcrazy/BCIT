package ca.bcit.comp2522.assignment7.question2;

/*
 * The following class cannot be compiled, because Java cannot
 * compare two instances without knowing their data type.

public final class Algorithm {
    public static <T> T max(T x, T y) {
        return x > y ? x : y;
    }
}
*/

// However, we can modify the code and implement the comparison
public final class Algorithm {
    public static <T extends Comparable<T>> T max(T x, T y) {
        return x.compareTo(y) > 0 ? x : y;
    }
}