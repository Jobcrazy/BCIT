package ca.bcit.winter2021.comp2522.midterm.question5;

//TODO: complete the definition the class
public class Cylindrical extends Item{
    Cylindrical( int radius, int height, String color ){
        this.radius = radius;
        this.height = height;
        this.color = color;
        this.shape = SHAPE_CYLINDER;
    }

    @Override
    public double getVolume() {
        return 3.14 * radius * radius * height;
    }
}


