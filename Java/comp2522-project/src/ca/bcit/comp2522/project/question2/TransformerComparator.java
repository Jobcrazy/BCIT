package ca.bcit.comp2522.project.question2;

import java.util.Comparator;
import java.util.function.Function;

public class TransformerComparator implements Comparator<Transformer> {
    private int compareCourageAndStrength(Transformer o1, Transformer o2) {
        if (o1.getCourage() - o2.getCourage() >= 4 &&
                o1.getStrength() - o2.getStrength() >= 3) {
            return 1;
        } else if (o2.getCourage() - o1.getCourage() >= 4 &&
                o2.getStrength() - o1.getStrength() >= 3) {
            return -1;
        }
        return 0;
    }

    private int compareSkill(Transformer o1, Transformer o2) {
        if (o1.getSkill() - o2.getSkill() >= 3) {
            return 1;
        } else if (o2.getSkill() - o1.getSkill() >= 3) {
            return -1;
        }
        return 0;
    }

    private int compareOverAll(Transformer o1, Transformer o2) {
        Function<Transformer, Integer> overAllAlgorithm = (t) ->
                t.getStrength() + t.getIntelligence()
                        + t.getSpeed() + t.getEndurance() + t.getFirepower();
        Integer overAll1 = o1.getOverall(overAllAlgorithm);
        Integer overAll2 = o1.getOverall(overAllAlgorithm);
        if (overAll1 > overAll2) {
            return 1;
        } else if (overAll1 < overAll2) {
            return -1;
        }
        return 0;
    }

    @Override
    public int compare(Transformer o1, Transformer o2) {
        if (0 != compareCourageAndStrength(o1, o2)) {
            return compareCourageAndStrength(o1, o2);
        }

        //o Otherwise, if one of the fighters is 3 or more points of skill above
        // their opponent, they win the fight regardless of overall rating
        else if (0 != compareSkill(o1, o2)) {
            return compareSkill(o1, o2);
        }

        //o The winner is the Transformer with the highest overall rating, may
        // a draw game
        return compareOverAll(o1, o2);
    }
}
