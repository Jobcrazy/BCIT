package ca.bcit.comp2522.assignment8.question2;

public class ApplicationDriver {
    enum ShapeType {
        Rectangle,
        Triangle,
        Circle,
        Square,
        Custom
    }

    public static void test() {
        int[] s1Sides = {10, 20};
        Shape s1 = new Shape(ShapeType.Rectangle, s1Sides) {
            //complete the implementation of the anonymous inner class here
            @Override
            float perimeter() {
                return 2 * (sides[0] + sides[1]);
            }

            @Override
            float area() {
                return sides[0] * sides[1];
            }
        };

        System.out.println("S1's perimeter is: " + s1.perimeter());
        System.out.println("S1's area is: " + s1.area());

        int[] s2Sides = {10};
        Shape s2 = new Shape(ShapeType.Square, s2Sides) {
            //complete the implementation of the anonymous inner class here
            @Override
            float perimeter() {
                return 4 * sides[0];
            }

            @Override
            float area() {
                return sides[0] * sides[0];
            }
        };

        System.out.println("S2's perimeter is: " + s2.perimeter());
        System.out.println("S2s area is: " + s2.area());

        int[] s3Sides = {12};
        Shape s3 = new Shape(ShapeType.Circle, s3Sides) {
            //complete the implementation of the anonymous inner class here
            @Override
            float perimeter() {
                return (float) (2 * Math.PI * sides[0]);
            }

            @Override
            float area() {
                return (float) (Math.PI * sides[0] * sides[0]);
            }
        };

        System.out.println("S3's perimeter is:" + s3.perimeter());
        System.out.println("S3s area is: " + s3.area());

        int[] s4Sides = {8, 12, 12};
        Shape s4 = new Shape(ShapeType.Triangle, s4Sides) {
            //complete the implementation of the anonymous inner class here
            @Override
            float perimeter() {
                return sides[0] + sides[1] + sides[2];
            }

            @Override
            float area() {
                // 1/4sqrt[(a+b+c)(a+b-c)(a+c-b)(b+c-a)]
                int a = sides[0], b = sides[1], c = sides[2];
                return (float) (0.25 * Math.sqrt(
                        (a + b + c) * (a + b - c) * (a + c - b) * (b + c - a)
                ));
            }
        };

        System.out.println("S4's perimeter is: " + s4.perimeter());
        System.out.println("S4s area is: " + s4.area());
    }
}
