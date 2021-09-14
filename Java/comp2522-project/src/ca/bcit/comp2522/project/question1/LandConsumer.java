package ca.bcit.comp2522.project.question1;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LandConsumer implements Consumer<ArrayList<Integer>> {
    private final int STATE_UNKNOWN = -1;
    private final int STATE_STARTED = 0;
    private final int STATE_FALLING = 1;
    private final int STATE_RAISING = 2;
    private final int STATE_FLAT = 3;

    private int getCurrentState(int lastState, Integer lastLand,
                                Integer currentLand) {
        if (STATE_UNKNOWN == lastState) {
            return STATE_STARTED;
        }

        if (lastLand > currentLand) {
            return STATE_FALLING;
        } else if (lastLand < currentLand) {
            return STATE_RAISING;
        } else {
            return STATE_FLAT;
        }
    }

    public int getCastleLands(ArrayList<Integer> landList) {
        int totalCastleLands = 0;
        int lastLand = 0;
        int lastKeyState = STATE_UNKNOWN;
        int distance = 0;
        int currentState;

        if (0 == landList.size()) {
            return 0;
        }

        for (Integer land : landList) {
            currentState = getCurrentState(lastKeyState, lastLand, land);

            if (currentState == STATE_STARTED) {
                lastKeyState = STATE_STARTED;
                totalCastleLands++;
            } else if (lastKeyState == STATE_STARTED &&
                    (currentState == STATE_FALLING || currentState == STATE_RAISING)) {
                lastKeyState = currentState;
                totalCastleLands += distance;
                distance = 0;
            } else if (lastKeyState == STATE_FALLING && currentState == STATE_RAISING ||
                    lastKeyState == STATE_RAISING && currentState == STATE_FALLING
            ) {
                totalCastleLands += distance + 1;
                distance = 0;
                lastKeyState = currentState;
            }

            lastLand = land;
        }

        return totalCastleLands + (lastKeyState == STATE_STARTED ?
                distance : distance + 1);
    }

    @Override
    public void accept(ArrayList<Integer> landList) {
        System.out.printf("We can build %d castle(s) on lands [",
                getCastleLands(landList));
        for (int index = 0; index < landList.size(); ++index) {
            if (index != landList.size() - 1)
                System.out.printf("%d,", landList.get(index));
            else
                System.out.printf("%d", landList.get(index));
        }

        System.out.println("]");
    }
}
