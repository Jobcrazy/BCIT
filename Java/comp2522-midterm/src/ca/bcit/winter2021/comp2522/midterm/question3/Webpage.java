package ca.bcit.winter2021.comp2522.midterm.question3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Webpage {
    private ArrayList<TwitterAccount> accounts;

    Webpage() {
        accounts = new ArrayList<>();
    }

    //TODO: Add proper methods if needed
    boolean addAccount(TwitterAccount account){
        if (null == account){
            return false;
        }
        accounts.add(account);
        return true;
    }

    void sort(Comparator<TwitterAccount> sorter){
        accounts.sort(sorter);
    }

    //TODO: fix the compile error below
    //The following code does not work because the sort method has not been defined yet.
    //Please fix the issue without changing the methods below
    //Please fix the issue without changing the methods below
    public void sortBasedOnNumberOfFollowers() {
        this.sort(new FollowerSorter());
    }

    public void sortBasedOnNumberOfFollowing() {
        this.sort(new FollowingSorter());
    }

    //TODO: Changes allowed to this method
    public ArrayList<TwitterAccount> filterBasedOnYearJoinTwitter(int year) {
        //TODO: Complete implementation of this method to return all twitter accounts which have joined on or before the given input year
        ArrayList<TwitterAccount> filteredAccounts = new ArrayList<>();

        for (TwitterAccount account: accounts){
            if (account.getYearOfJoinTwitter() < year || account.getYearOfJoinTwitter() == year){
                filteredAccounts.add(account);
            }
        }

        return filteredAccounts;
    }
}
