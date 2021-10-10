package ca.bcit.liuhang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseContributor {
    @SerializedName("contributors")
    @Expose
    private ArrayList<Contributor> contributors = new ArrayList<>();

    public ArrayList<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<Contributor> contributors) {
        this.contributors = contributors;
    }
}
