package ca.bcit.comp2522.project.question2;

import java.util.function.Function;

public class Transformer {
    private final String name;
    private final String type;
    private final int strength;
    private final int intelligence;
    private final int speed;
    private final int endurance;
    private final int rank;
    private final int courage;
    private final int firepower;
    private final int skill;

    static class RobotType{
        public final static int TYPE_KNOW = 0;
        public final static String TYPE_AUTOBOT = "A";
        public final static String TYPE_DECEPTICONS = "D";
    }

    public Transformer(String name, String type, int strength, int intelligence,
                       int speed, int endurance, int rank, int courage,
                       int firepower, int skill) {
        this.name = name;
        this.type = type;
        this.strength = strength;
        this.intelligence = intelligence;
        this.speed = speed;
        this.endurance = endurance;
        this.rank = rank;
        this.courage = courage;
        this.firepower = firepower;
        this.skill = skill;
    }

    public Integer getOverall(Function<Transformer, Integer> overAllAlgorithm) {
        return overAllAlgorithm.apply(this);
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getCourage() {
        return courage;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getFirepower() {
        return firepower;
    }

    public int getRank() {
        return rank;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getSkill() {
        return skill;
    }

    public int getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }
}
