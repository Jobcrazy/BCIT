package ca.bcit.winter2021.comp2522.midterm.question3;

import java.util.Comparator;

//TODO: Complete definition of this class
public class FollowerSorter implements Comparator<TwitterAccount> {
    @Override
    public int compare(TwitterAccount o1, TwitterAccount o2) {
        if (o1.getNumberOfFollowers() == o2.getNumberOfFollowers()){
            return 0;
        }else if (o1.getNumberOfFollowers() < o2.getNumberOfFollowers()){
            return -1;
        };
        return 1;
    }
}
