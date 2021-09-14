package ca.bcit.winter2021.comp2522.midterm.question5;

//TODO: complete the definition the class
public class Cube extends Item{
    Cube( int width, int length, int height, String color ){
        this.width = width;
        this.length = length;
        this.height = height;
        this.color = color;
        this.shape = SHAPE_CUBE;
    }

    @Override
    public double getVolume() {
        return width * length * height;
    }
}
