package ca.bcit.comp2522.assignment10.question2;

import java.util.HashMap;
import java.util.Map;

public class Question2 {
    public void loopMapForEach() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("D", 40);
        map.put("E", 50);
        map.put("F", 60);

        map.forEach((k, v)->{
            System.out.println("Key : " + k + ", Value : " + v);
        });
    }

    public void test() {
        System.out.println("-----Question 2-----");
        loopMapForEach();
    }
}
