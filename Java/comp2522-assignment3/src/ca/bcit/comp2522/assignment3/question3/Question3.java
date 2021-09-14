package ca.bcit.comp2522.assignment3.question3;

public class Question3 {
    final int SHAPE1 = 1;
    final int SHAPE2 = 2;
    final int SHAPE3 = 3;

    private void printShape1() {
        for (int numberOfStars = 5, numberOfSpaces = 0;
             numberOfStars > 0;
             numberOfStars -= 2, numberOfSpaces++) {
            for (int spaceIndex = 0; spaceIndex < numberOfSpaces; spaceIndex++) {
                System.out.print(" ");
            }

            for (int starIndex = 0; starIndex < numberOfStars; starIndex++) {
                System.out.print("*");
            }

            System.out.print("\n");
        }
    }

    private void printShape2() {
        for (int numberOfStars = 5;
             numberOfStars > 0;
             numberOfStars--) {

            for (int starIndex = 0; starIndex < numberOfStars; starIndex++) {
                System.out.print("*");
            }

            System.out.print("\n");
        }
    }

    private void printShape3() {
        for (int numberOfStars = 1;
             numberOfStars <= 5;
             numberOfStars++) {

            for (int starIndex = 0; starIndex < numberOfStars; starIndex++) {
                System.out.print("*");
            }

            System.out.print("\n");
        }
    }

    public void printStar(int shape) {
        if (SHAPE1 == shape) {
            printShape1();
        } else if (SHAPE2 == shape) {
            printShape2();
        } else if (SHAPE3 == shape) {
            printShape3();
        }
    }

    public void test() {
        printStar(SHAPE1);
        printStar(SHAPE2);
        printStar(SHAPE3);
    }
}
