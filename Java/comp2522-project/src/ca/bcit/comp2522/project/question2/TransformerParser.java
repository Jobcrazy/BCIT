package ca.bcit.comp2522.project.question2;

import java.util.ArrayList;
import java.util.function.Function;

public class TransformerParser implements Function<String, ArrayList<Transformer>> {
    private static class PropertyIndex{
        private static final int NAME = 0;
        private static final int TYPE = 1;
        private static final int STRENGTH = 2;
        private static final int INTELLIGENCE = 3;
        private static final int SPEED = 4;
        private static final int ENDURANCE = 5;
        private static final int RANK = 6;
        private static final int COURAGE = 7;
        private static final int FIREPOWER = 8 ;
        private static final int SKILL = 9;
    }

    @Override
    public ArrayList<Transformer> apply(String line) {
        ArrayList<Transformer> robots = new ArrayList<>();

        if (line.length() == 0){
            return robots;
        }

        line = line.replace(" ", "");
        String[] robotsString = line.split("\\*");

        for (String robotString : robotsString) {
            String []singleRobot = robotString.split(",");
            Transformer transformer = new Transformer(
                    singleRobot[PropertyIndex.NAME],
                    singleRobot[PropertyIndex.TYPE],
                    Integer.parseInt(singleRobot[PropertyIndex.STRENGTH]),
                    Integer.parseInt(singleRobot[PropertyIndex.INTELLIGENCE]),
                    Integer.parseInt(singleRobot[PropertyIndex.SPEED]),
                    Integer.parseInt(singleRobot[PropertyIndex.ENDURANCE]),
                    Integer.parseInt(singleRobot[PropertyIndex.RANK]),
                    Integer.parseInt(singleRobot[PropertyIndex.COURAGE]),
                    Integer.parseInt(singleRobot[PropertyIndex.FIREPOWER]),
                    Integer.parseInt(singleRobot[PropertyIndex.SKILL])
            );
            robots.add(transformer);
        }

        return robots;
    }
}
