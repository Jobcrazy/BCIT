package ca.bcit.winter2021.comp2522.midterm.question3;

public class TestQuestion3 {
    public static void runAllTestScenariosForQuestion3() {

        Webpage webpage = new Webpage();
        //TODO: complete creating webpage instance

        TwitterAccount barackObamaAccount = new TwitterAccount(
                "Barack Obama", 129600000, 594500, 2007);
        //TODO: create an instance for @barackObama

        TwitterAccount joeBidenAccount = new TwitterAccount(
                "Joe Biden", 28800000, 47, 2007);
        //TODO: create an instance for @JoeBiden

        TwitterAccount elonmuskAccount = new TwitterAccount(
                "Elon Musk", 47900000, 103, 2009);
        //TODO: create an instance for @elonmusk

        TwitterAccount billgatesAccount =  new TwitterAccount(
                "Bill Gates", 53800000, 274, 2009);
        //TODO: create an instance for @BillGates

        TwitterAccount ladygagaAccount = new TwitterAccount(
                "Lady Gaga", 83900000, 120400, 2008);
        //TODO: create an instance for @ladygaga

        TwitterAccount rihannaAccount = new TwitterAccount(
                "Rihanna", 102100000, 1015, 2009);
        //TODO: create an instance for @rihanna

        TwitterAccount michelleObamaAccount = new TwitterAccount(
                "Michelle Obama", 20300000, 15, 2011);;
        //TODO: create an instance for @Michelle Obama


        //TODO: add anything else needed here (if needed)
        webpage.addAccount(barackObamaAccount);
        webpage.addAccount(joeBidenAccount);
        webpage.addAccount(elonmuskAccount);
        webpage.addAccount(billgatesAccount);
        webpage.addAccount(ladygagaAccount);
        webpage.addAccount(rihannaAccount);
        webpage.addAccount(michelleObamaAccount);

        //Note: Do not make changes to the following statements
        //Note: Do not make changes to the following statements
        webpage.sortBasedOnNumberOfFollowers();
        webpage.sortBasedOnNumberOfFollowing();
        webpage.filterBasedOnYearJoinTwitter(2015);

    }
}
