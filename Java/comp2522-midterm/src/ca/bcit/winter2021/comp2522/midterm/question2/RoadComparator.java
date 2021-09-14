package ca.bcit.winter2021.comp2522.midterm.question2;

import java.util.Comparator;

public class RoadComparator implements Comparator<Road> {
    @Override
    public int compare(Road o1, Road o2) {
        if (o1.getDistance() == o2.getDistance()) {
            return 0;
        } else if (o1.getDistance() < o2.getDistance()) {
            return 1;
        }
        return -1;

    }
}
