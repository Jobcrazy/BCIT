package ca.bcit.comp2522.samplemidterm.Question2;

public class Ellipse {
    private Integer xCoefficient = 0;
    private Integer yCoefficient = 0;
    private Integer result = 0;

    public Ellipse(Integer xCoefficient,
                   Integer yCoefficient,
                   Integer result){
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
        this.result = result;
    }

    public void printEllipse(){
        System.out.printf("%dX^2 + %dY^2 = %d\n",
                xCoefficient,
                yCoefficient,
                result);
    }
}
