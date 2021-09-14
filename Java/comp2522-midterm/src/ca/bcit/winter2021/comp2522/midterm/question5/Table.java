package ca.bcit.winter2021.comp2522.midterm.question5;

import java.util.ArrayList;

//TODO: Complete the definition of this class.
public class Table {
    private ArrayList<Item> items;

    Table() {
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public double getTotalVolume() {
        double total = 0f;

        for (Item item : items) {
            total += item.getVolume();
        }

        return total;
    }

    public double getShapeVolume(int shape) {
        double total = 0f;

        for (Item item : items) {
            if (item.getShape() == shape)
                total += item.getVolume();
        }

        return total;
    }

    public double getTotalVolumeOfCuboids(){
        return getShapeVolume(Item.SHAPE_CUBOID);
    }

    public double getColorVolume(String color) {
        double total = 0f;

        for (Item item : items) {
            if (item.getColor().equals(color))
                total += item.getVolume();
        }
        return total;
    }

    public double getTotalVolumeOfYellowItems(){
        return getColorVolume("Yellow");
    }
}
