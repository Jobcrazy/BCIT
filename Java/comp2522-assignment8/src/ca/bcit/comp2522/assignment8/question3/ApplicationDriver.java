package ca.bcit.comp2522.assignment8.question3;

public class ApplicationDriver {
    class MyGeometry {
        public void calculateAreaAndPerimeter(Shape shape) {
            if (!Custom.class.isInstance(shape)) {
                shape.perimeter();
                shape.area();
            } else {
                shape.perimeter();
            }
        }
    }

    enum ShapeType {
        Rectangle,
        Triangle,
        Circle,
        Square,
        Custom
    }

    private abstract class Custom extends Shape {
        Custom(ShapeType shape, int[] sides) {
            super(shape, sides);
        }

        abstract float perimeter();

        @Override
        final float area() {
            System.out.println("Custom's area is ignored.");
            return 0;
        }
    }

    public void test() {
        MyGeometry mg = new MyGeometry();

        int[] s1Sides = {10, 20}; //10 is width and 20 is length
        ShapeType s1Type = ShapeType.Rectangle;
        //use mg.calculateAreaAndPerimeter to calculate perimeter and area
        // for the Rectangle above
        mg.calculateAreaAndPerimeter(new Shape(s1Type, s1Sides) {
            @Override
            float perimeter() {
                float result = 2 * (sides[0] + s1Sides[1]);
                System.out.println("S1's perimeter is: " + result);
                return result;
            }

            @Override
            float area() {
                float result = sides[0] * s1Sides[1];
                System.out.println("S1's area is: " + result);
                return result;
            }
        });

        int[] s2Sides = {10}; //10 is the width
        ShapeType s2Type = ShapeType.Square;
        //use mg.calculateAreaAndPerimeter to calculate perimeter and area
        // for the Square above
        mg.calculateAreaAndPerimeter(new Shape(s2Type, s2Sides) {
            @Override
            float perimeter() {
                float result = 4 * sides[0];
                System.out.println("S2's perimeter is: " + result);
                return result;
            }

            @Override
            float area() {
                float result = sides[0] * s1Sides[0];
                System.out.println("S2's area is: " + result);
                return result;
            }
        });

        int[] s3Sides = {12}; //12 is the radius
        ShapeType s3Type = ShapeType.Circle;
        //use mg.calculateAreaAndPerimeter to calculate perimeter and area
        // for the Circle above
        mg.calculateAreaAndPerimeter(new Shape(s3Type, s3Sides) {
            @Override
            float perimeter() {
                float result = (float) (2 * Math.PI * sides[0]);
                System.out.println("S3's perimeter is: " + result);
                return result;
            }

            @Override
            float area() {
                float result = (float) (Math.PI * sides[0] * sides[0]);
                System.out.println("S3's area is: " + result);
                return result;
            }
        });

        int[] s4Sides = {8, 12, 12};
        ShapeType s4Type = ShapeType.Custom;
        //use mg.calculateAreaAndPerimeter to calculate perimeter and area
        // for the Custom above
        mg.calculateAreaAndPerimeter(new Custom(s4Type, s4Sides) {
            @Override
            float perimeter() {
                float result =  s4Sides[0] + s4Sides[1] + s4Sides[2];
                System.out.println("S4's perimeter is: " + result);
                return result;
            }
        });
    }
}
