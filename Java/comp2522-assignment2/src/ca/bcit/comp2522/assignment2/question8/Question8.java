package ca.bcit.comp2522.assignment2.question8;

public class Question8 {
    public static double monteCarlo(int times) {
        long counter = 0;
        for (int index = 0; index < times; index++) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y < 1) {
                counter++;
            }
        }
        return 4 * (double) counter / (double) times;
    }

    public static void testMonteCarlo() {
        System.out.println("Mante Carlo 100 times:" + monteCarlo(100));
        System.out.println("Mante Carlo 1000 times:" + monteCarlo(1000));
        System.out.println("Mante Carlo 10000 times:" + monteCarlo(10000));
    }
}
