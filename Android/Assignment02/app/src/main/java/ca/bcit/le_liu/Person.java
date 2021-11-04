package ca.bcit.le_liu;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Person {
    public String id;
    public String Age_Group;
    public String Classification_Reported;
    public String HA;
    public String Reported_Date;
    public String Sex;

    public Person() {
    }

    public Person(String age_Group, String classification_Reported,
                  String hA, String reported_Date, String sex) {
        Age_Group = age_Group;
        Classification_Reported = classification_Reported;
        HA = hA;
        Reported_Date = reported_Date;
        Sex = sex;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Age_Group", Age_Group);
        result.put("Classification_Reported", Classification_Reported);
        result.put("HA", HA);
        result.put("Reported_Date", Reported_Date);
        result.put("Sex", Sex);
        return result;
    }
}
