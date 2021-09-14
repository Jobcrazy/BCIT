package ca.bcit.comp2522.project.question2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;

public class Game {
    private final Team autobots = new Team();
    private final Team decepticons = new Team();
    private final String robotFilePath;
    private int gameCounter = 0;

    public static class BattleResult {
        public static final int RESULT_UNKNOWN = -2;
        public static final int RESULT_LOSE = -1;
        public static final int RESULT_DRAW = 0;
        public static final int RESULT_WIN = 1;
    }

    public Game(String robotFilePath) {
        this.robotFilePath = robotFilePath;
    }

    public void loadGame(
            Function<String, ArrayList<Transformer>> transformerReader,
            Comparator<Transformer> comparator) {
        Scanner in = null;
        try {
            in = new Scanner(new File(robotFilePath));
        } catch (Exception e) {
            System.out.println("Robot file doesn't exist!");
            return;
        }


        while (in.hasNextLine()) {
            ArrayList<Transformer> robots = transformerReader.apply(in.nextLine());
            if (0 == robots.size()) {
                startGame(comparator);
            } else {
                for (Transformer transformer : robots) {
                    if (transformer.getType().equals(Transformer.RobotType.TYPE_DECEPTICONS)) {
                        decepticons.addMember(transformer);
                    } else if (transformer.getType().equals(Transformer.RobotType.TYPE_AUTOBOT)) {
                        autobots.addMember(transformer);
                    }
                }
            }
        }

        startGame(comparator);
        in.close();
    }

    private void startGame(Comparator<Transformer> comparator) {
        if (autobots.getMembers().size() == 0 &&
                decepticons.getMembers().size() == 0) {
            System.out.println("***Game Over***\n");
            return;
        }

        System.out.println("---Game " + ++gameCounter + " Start---");

        //Game Logic Here
        //1. Print Number of Battles
        int autobotSize = autobots.getMembers().size();
        int decepticonsSize = decepticons.getMembers().size();
        int round = Math.min(autobotSize, decepticonsSize);
        System.out.println("The number of battles:" + round);

        //2. Battle and count survivors
        int autobotWins = 0, decepticonsWins = 0;
        for (int index = 0; index < round; ++index) {
            int specialCompareResult = specialCompare(autobots.getMembers().get(index),
                    decepticons.getMembers().get(index));
            int compareResult = comparator.compare(autobots.getMembers().get(index),
                    decepticons.getMembers().get(index));

            if (specialCompareResult == BattleResult.RESULT_DRAW) {
                autobotSize = decepticonsSize = 0;
                autobotWins = decepticonsWins = 0;
                break;
            } else if (specialCompareResult == BattleResult.RESULT_WIN ||
                    compareResult == BattleResult.RESULT_WIN) {
                autobotWins += 1;
            } else if (specialCompareResult == BattleResult.RESULT_LOSE ||
                    compareResult == BattleResult.RESULT_LOSE) {
                // autobotSize -= 1;
                decepticonsWins += 1;
            } else if (compareResult == BattleResult.RESULT_DRAW) {
                autobotSize -= 1;
                decepticonsSize -= 1;
            }
        }

        if (autobotWins == decepticonsWins) {
            System.out.println("Draw Game!");
        } else if (autobotWins < decepticonsWins) {
            System.out.printf("Decepticons Win! \n%d autobot remains\n",
                    autobotSize);
        } else {
            System.out.printf("Autobot Win! \n%d decepticons remains\n",
                    decepticonsSize);
        }

        autobots.clear();
        decepticons.clear();
        System.out.println("***Game Over***\n");
    }

    private int specialCompare(Transformer o1, Transformer o2) {
        if ((o1.getName().equals("OptimusPrime") || o1.getName().equals("Predaking"))
                && (!o2.getName().equals("OptimusPrime") && !o2.getName().equals("Predaking"))
        ) {
            return BattleResult.RESULT_WIN;
        } else if ((o2.getName().equals("OptimusPrime") || o2.getName().equals("Predaking"))
                && (!o1.getName().equals("OptimusPrime") && !o1.getName().equals("Predaking"))
        ) {
            return BattleResult.RESULT_LOSE;
        } else if ((o1.getName().equals("OptimusPrime") || o1.getName().equals("Predaking"))
                && (o1.getName().equals("OptimusPrime") || o1.getName().equals("Predaking"))
        ) {
            return BattleResult.RESULT_DRAW;
        }

        return BattleResult.RESULT_UNKNOWN;
    }
}
