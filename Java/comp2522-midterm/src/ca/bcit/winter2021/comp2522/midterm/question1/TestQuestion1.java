package ca.bcit.winter2021.comp2522.midterm.question1;

public class TestQuestion1 {

    public static void runAllTestScenariosForQuestion1(){

        TV tv1 = new TV(10, 100, false, false);
        //TODO: create tv1 which is an instance of TV. The tv1 instance should be initialized with volume: 10, channel: 100, unmute and off.

        WhiteRemote whiteRemote = new WhiteRemote();
        BlackRemote blackRemote = new BlackRemote();
        //TODO: create an instance for the whiteRemote and blackRemote.


        //TODO: use the whiteRemote instance to turn on the tv1
        whiteRemote.turnOnOFFTV(tv1);

        //TODO: use the whiteRemote object to change the tv1's volume to 13
        whiteRemote.increaseVolume(3, tv1); // 10 + 3 = 13

        //TODO: use the whiteRemote object to change the tv1's volume back to 11
        whiteRemote.decreaseVolume(2, tv1); // 13 - 2 = 11

        //TODO: use the blackRemote object to do change the tv1's current channle to 103
        blackRemote.increaseChannel(3, tv1);  // 100 + 3 = 103

        //TODO: use the blackRemote instance to mute the tv1
        blackRemote.muteUnMuteTV(tv1); // muted = !muted

        //TODO: use the whiteRemote instance to unmute the tv1
        whiteRemote.muteUnMuteTV(tv1);

        //TODO: Make sure the following print statement, prints out the information of the TV (volume, channle, mute or unmute, on or off)
        //Note: Do not make changes to this print statement
        //Note: Do not make changes to this print statement
        System.out.println(tv1);

        //TODO: add anything else needed here (if needed)

        //Note: Do not make changes to the following statements
        //Note: Do not make changes to the following statements
        System.out.println("TV Volume Test: "+testTVVolume(tv1));
        System.out.println("TV Mute Test: "+isTVMute(tv1));
        System.out.println("TV Off Test: "+isTVOff(tv1));
        System.out.println("TV Channel Test: "+testTVChannel(tv1));
    }

    //TODO: There are 4 compile errors here. Do not make changes here. Make changes in the right place to fix these issues.
    //The following code does not compile as there are some compile errors. Please fix them without making any change in the code below.
    //Note: Do not change this method
    //Note: Do not change this method

    private static boolean testTVVolume(TV tv){
        if(tv.getVolume()==13){
            return true;
        } else {
            return false;
        }
    }

    //Do not change this method
    //Do not change this method
    private static boolean isTVMute(TV tv){
        return tv.isMute();
    }

    //Do not change this method
    //Do not change this method
    private static boolean isTVOff(TV tv){
        return !(tv.isTVON());
    }

    //Do not change this method
    //Do not change this method
    private static boolean testTVChannel(TV tv){
        if (tv.getChannel()==103){
            return true;
        } else {
            return false;
        }
    }
}
