package ca.bcit.winter2021.comp2522.midterm.question2;

import java.util.ArrayList;
import java.util.Collections;

public class TransitMap {
    private ArrayList<Road> roads;

    TransitMap() {
        roads = new ArrayList<>();
    }

    public void addRoad(Road newRoad) {
        for (Road road : roads) {
            if (road.equals(newRoad)) {
                return;
            }
        }
        roads.add(newRoad);
    }

    public ArrayList<Double> getDistances(String cityName) {
        ArrayList<Double> distances = new ArrayList<>();

        for (Road road : roads) {
            if (road.isCityConnected(cityName)) {
                String[] cities = road.getCities();
                System.out.printf("%s to %s: %.2f\n", cities[0], cities[1], road.getDistance());
            }
        }

        return distances;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void sortRoadsDesc(){
        roads.sort(new RoadComparator());
    }

    public static String[] getBiggestDistance(TransitMap map) {
        ArrayList<Road> roads = map.getRoads();
        return Collections.max(roads).getCities();
    }
}
