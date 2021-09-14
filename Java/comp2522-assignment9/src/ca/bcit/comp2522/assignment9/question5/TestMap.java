package ca.bcit.comp2522.assignment9.question5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class TestMap {
    HashMap<String, Predicate<String>> map;

    TestMap() {
        map = new HashMap<>();
    }

    public void addPair(String name, Predicate<String> predicate) {
        map.put(name, predicate);
    }

    public HashMap<String, ArrayList<String>> organizeNames(List<String>
                                                                    name) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        //map is a hashmap where its keys are the name of the predicate and
        //its values is a list of names to which the corresponding predicate
        // holds. Complete the implementation of this method
        this.map.forEach((k, v) -> {
            ArrayList<String> names = new ArrayList<>();
            for (String item : name) {
                if (v.test(item)) {
                    names.add(item);
                }
            }
            map.put(k, names);
        });

        return map;
    }

    public static void main(String[] args) {
        TestMap testMap = new TestMap();
        testMap.addPair("Predicate1", s -> s.length() >= 4);
        testMap.addPair("Predicate2", s -> s.contains("d"));
        testMap.addPair("Predicate3", s -> s.charAt(0) == 'a');

        List<String> names = new ArrayList<String>();
        names.add("Java");
        names.add("is");
        names.add("a");
        names.add("great");
        names.add("language");
        HashMap<String, ArrayList<String>> result =
                testMap.organizeNames(names);

        //The result should be like:
        // Predicate1: ("Java","great", "language")
        // Predicate2: ()
        // Predicate3: (a)
        result.forEach((k, v) -> {
            System.out.printf("%s: (", k);
            v.forEach(name->{
                System.out.printf("\"%s\" ", name);
            });
            System.out.println(")");
        });
    }
}
