package ca.bcit.winter2021.comp2522.midterm.question1;

public class TV {
    private int volume;
    private int channel;
    private boolean muted;
    private boolean tvOn;

    public TV(int volume, int channel, boolean muted, boolean powerOn) {
        this.volume = volume;
        this.channel = channel;
        this.muted = muted;
        this.tvOn = powerOn;
    }

    public int getVolume(){
        return volume;
    }

    public int getChannel() {
        return channel;
    }

    public boolean isMute() {
        return !muted;
    }

    public boolean isTVON() {
        return tvOn;
    }

    public int changeVolume(int amount) {
        volume += amount;
        return volume;
    }

    public int changeChannel(int amount) {
        channel += amount;
        return channel;
    }

    public boolean muteUnMuteTV(){
        muted = !muted;
        return this.muted;
    }

    public boolean turnOnOFFTV(){
        tvOn = !tvOn;
        return tvOn;
    }
}
