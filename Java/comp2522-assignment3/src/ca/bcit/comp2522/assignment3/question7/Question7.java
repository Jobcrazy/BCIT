package ca.bcit.comp2522.assignment3.question7;

public class Question7 {
    private static final int NUMBER_NOT_EXIST = -1;

    public static int searchNumber(int[] numberList, int numberToSearch) {
        for (int index = 0; index < numberList.length; index++) {
            if (numberList[index] == numberToSearch) {
                return index;
            }
        }
        return NUMBER_NOT_EXIST;
    }

    public static void test() {
        int[] numberList = {1, 4, 5, 6, 1, 2, 4, 5, 6, 5};
        System.out.println("Number list: {1, 4, 5, 6, 1, 2, 4, 5, 6, 5}");
        System.out.println("Number to find: 5");

        System.out.printf("Index: %d\n", searchNumber(numberList, 5));
    }
}
