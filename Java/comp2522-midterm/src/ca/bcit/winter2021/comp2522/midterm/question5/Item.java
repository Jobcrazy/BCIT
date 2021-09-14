package ca.bcit.winter2021.comp2522.midterm.question5;

public abstract class Item {
    protected int width;
    protected int length;
    protected int height;
    protected int radius;
    protected String color;
    protected int shape;

    public final static int SHAPE_CUBE = 1;
    public final static int SHAPE_CUBOID = 2;
    public final static int SHAPE_CYLINDER = 3;

    public abstract double getVolume();

    public int getShape() {
        return shape;
    }

    public String getColor(){
        return color;
    }
}
