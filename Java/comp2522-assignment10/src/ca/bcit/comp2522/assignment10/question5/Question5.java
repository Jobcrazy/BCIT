package ca.bcit.comp2522.assignment10.question5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question5 {
    public void test() {
        System.out.println("-----Question 5-----");

        String[] all = new String[]{"A", "B", "C", "A", "B", "C", "D", "B", "D", "F", "H"};
        List<String> words = Arrays.asList(all);

        Map<String, Integer> result = words.stream().collect(
                Collectors.toMap(word -> word,
                        word -> 1,
                        (oldValue, newValue) -> oldValue + 1
                ));
        System.out.println(result);
    }
}
