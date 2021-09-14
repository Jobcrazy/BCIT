package ca.bcit.comp2522.assignment8.question2;

public abstract class Shape {
    abstract float perimeter();

    abstract float area();

    ApplicationDriver.ShapeType shape;
    int[] sides;
    Shape(ApplicationDriver.ShapeType shape, int[] sides){
        this.shape = shape;
        this.sides = sides;
    }
}