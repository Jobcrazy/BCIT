package ca.bcit.comp2522.assignment1.question5;

import java.util.Scanner;

public class Question5 {
    public void circleArea(){
        System.out.print("Please input the radius:");

        Scanner in = new Scanner(System.in);
        double radius = in.nextDouble();
        in.close();

        double area = 3.14 * radius * radius;
        System.out.printf("The area is: %.2f\n", area);
    }
}
