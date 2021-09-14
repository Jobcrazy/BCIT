package ca.bcit.winter2021.comp2522.midterm.question3;

import java.util.Comparator;

public class FollowingSorter implements Comparator<TwitterAccount> {
    @Override
    public int compare(TwitterAccount o1, TwitterAccount o2) {
        if (o1.getNumberOfFollowings() == o2.getNumberOfFollowings()){
            return 0;
        }else if (o1.getNumberOfFollowings() < o2.getNumberOfFollowings()){
            return -1;
        };
        return 1;
    }
}
