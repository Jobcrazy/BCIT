package ca.bcit.winter2021.comp2522.midterm.question5;

import ca.bcit.winter2021.comp2522.midterm.question5.Cube;
import ca.bcit.winter2021.comp2522.midterm.question5.Cuboid;
import ca.bcit.winter2021.comp2522.midterm.question5.Cylindrical;
import ca.bcit.winter2021.comp2522.midterm.question5.Table;

public class TestQuestion6 {
    public static void runAllTestScenariosForQuestion6(){
        //TODO: create an instance for every items shown on the table in the project desription.
        Cube cube = new Cube(1,1,1,"Red");
        Cuboid cuboid = new Cuboid(1,2,3,"Green");
        Cylindrical cylindrical = new Cylindrical(1, 5, "Yellow");


        ca.bcit.winter2021.comp2522.midterm.question5.Table table = new Table();
        //TODO: Create an instance of table.

        getTotalVolume(table);
        getTotalVolumeOfCuboids(table);
        getTotalVolumeOfYellowItems(table);
    }


    //Note: Do not change these methods
    //Note: Do not change these methods
    //Note: Do not change these methods

    private static double getTotalVolume(Table table){
        return table.getTotalVolume();
    }

    private static double getTotalVolumeOfCuboids(Table table){
        return table.getTotalVolumeOfCuboids();
    }

    private static double getTotalVolumeOfYellowItems(Table table){
        return table.getTotalVolumeOfYellowItems();
    }
}
