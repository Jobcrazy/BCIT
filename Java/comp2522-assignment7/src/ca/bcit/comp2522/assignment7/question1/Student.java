package ca.bcit.comp2522.assignment7.question1;

public class Student {
    private final Integer id;
    private final String fullName;
    private final Float score;

    public Student(Integer id, String fullName, Float score){
        this.id = id;
        this.fullName = fullName;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Float getScore() {
        return score;
    }
}
