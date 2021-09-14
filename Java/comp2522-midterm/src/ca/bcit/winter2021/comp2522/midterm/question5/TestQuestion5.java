package ca.bcit.winter2021.comp2522.midterm.question5;

public class TestQuestion5 {
    public static void runAllTestScenariosForQuestion5(){
        Table table = new Table();

        Cube cube1 = new Cube(1,1,1,"Red");
        Cube cube2 = new Cube(1,1,1,"Yellow");

        Cuboid cuboid1 = new Cuboid(1,2,3,"Green");
        Cuboid cuboid2 = new Cuboid(2,4,6,"Yellow");

        Cylindrical cylindrical1 = new Cylindrical(1, 5, "Yellow");

        table.addItem(cube1);
        table.addItem(cube2);
        table.addItem(cuboid1);
        table.addItem(cuboid2);
        table.addItem(cylindrical1);

        System.out.println("All volume: " + table.getTotalVolume());
        System.out.println("Cuboid volume: " + table.getShapeVolume(Item.SHAPE_CUBOID));
        System.out.println("Yellow volume: " + table.getColorVolume("Yellow"));
    }
}
