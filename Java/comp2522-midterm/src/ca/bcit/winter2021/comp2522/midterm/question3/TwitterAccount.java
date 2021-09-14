package ca.bcit.winter2021.comp2522.midterm.question3;

public class TwitterAccount {
    private String name;
    private int numberOfFollowers;
    private int numberOfFollowings;
    private int yearOfJoinTwitter;

    TwitterAccount(String name, int numberOfFollowers,
                   int numberOfFollowings, int yearOfJoinTwitter) {
        this.name= name;
        this.numberOfFollowers = numberOfFollowers;
        this.numberOfFollowings = numberOfFollowings;
        this.yearOfJoinTwitter = yearOfJoinTwitter;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public int getNumberOfFollowings() {
        return numberOfFollowings;
    }

    public int getYearOfJoinTwitter() {
        return yearOfJoinTwitter;
    }
}
