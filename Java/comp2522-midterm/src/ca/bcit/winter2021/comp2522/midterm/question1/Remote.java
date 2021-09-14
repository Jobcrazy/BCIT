package ca.bcit.winter2021.comp2522.midterm.question1;

public interface Remote {

    /**
     * The method will turn on the TV if it is off and turn off the TV if it is On.
     * @return the current status of the TV (On or Off)
     */
    public boolean turnOnOFFTV(TV tv);

    /**
     * Mutes the TV if it is unmute and the other way around.
     * @return the current status of the TV, whether mute or unmute
     */
    public boolean muteUnMuteTV(TV tv);

    /**
     * Volume up and down the TV's volume for one level. If amount is -1, it will volume down and if +1, if volumes up.
     * amount has to be either -1 or +1
     * @param amount
     * @return current volume after changing
     */
    public int changeVolume(int amount, TV tv);

    /**
     * channel up for one level if amount is +1 and channel down if the amount is -1. The amount has to be either +1 or -1
     * @param amount
     * @return the current channel
     */
    public int changeChannel(int amount, TV tv);
}
