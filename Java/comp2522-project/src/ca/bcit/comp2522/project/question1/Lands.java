package ca.bcit.comp2522.project.question1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Lands {
    private final ArrayList<ArrayList<Integer>> landList = new ArrayList<>();

    public Lands(String landFile) {
        try {
            Scanner in = new Scanner(new File(landFile));
            loadLands(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(landFile + " is not found.");
        }
    }

    private void loadLands(Scanner in) {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            line = line.replace("[", "");
            line = line.replace("]", "");

            String[] results = line.split(",");
            ArrayList<Integer> land = new ArrayList<>();
            for (String result : results) {
                try {
                    land.add(Integer.parseInt(result));
                } catch (Exception e){
                    System.out.println(result + "is not an integer, ignored.");
                }
            }

            landList.add(land);
        }
    }

    public void consumeLand(Consumer<ArrayList<Integer>> consumer){
        for (ArrayList<Integer> land: landList){
            consumer.accept(land);
        }
    }
}
