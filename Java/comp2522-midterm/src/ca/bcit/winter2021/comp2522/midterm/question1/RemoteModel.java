package ca.bcit.winter2021.comp2522.midterm.question1;

public class RemoteModel implements Remote {
    protected String color;

    @Override
    public boolean turnOnOFFTV(TV tv) {
        return tv.turnOnOFFTV();
    }

    @Override
    public boolean muteUnMuteTV(TV tv) {
        return tv.muteUnMuteTV();
    }

    @Override
    public int changeVolume(int amount, TV tv) {
        return tv.changeVolume(amount);
    }

    @Override
    public int changeChannel(int amount, TV tv) {
        return tv.changeChannel(amount);
    }

    public int increaseVolume(int amount, TV tv) {
        int currentVolume = 0;
        for (int number = 0; number < amount; number++) {
            currentVolume = changeVolume(1, tv);
        }
        return currentVolume;
    }

    public int decreaseVolume(int amount, TV tv) {
        int currentVolume = 0;
        for (int number = 0; number < amount; number++) {
            currentVolume = changeVolume(-1, tv);
        }
        return currentVolume;
    }

    public int decreaseChannel(int amount, TV tv) {
        int currentChannel = 0;
        for (int number = 0; number < amount; number++) {
            currentChannel = changeChannel(-1, tv);
        }
        return currentChannel;
    }

    public int increaseChannel(int amount, TV tv) {
        int currentChannel = 0;
        for (int number = 0; number < amount; number++) {
            currentChannel = changeChannel(1, tv);
        }
        return currentChannel;
    }
}
