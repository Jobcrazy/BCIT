package ca.bcit.winter2021.comp2522.midterm.question4;

public class TestQuestion4 {
    public static void runAllTestScenariosForQuestion4() {

        DatabaseManager dbManager = null;
        //TODO: Create a DatabaseManager's Object Pool with size N = 3
        dbManager = DatabaseManager.getInstance(3); //TODO: complete it here

        //TODO: Create 3 instances of DatabaseManagers
        DatabaseManager databaseManager1 = DatabaseManager.getInstance(0);
        DatabaseManager databaseManager2 = DatabaseManager.getInstance(0);
        DatabaseManager databaseManager3 = DatabaseManager.getInstance(0);

        //TODO: create another instance of DatabaseManager.
        //Note: Remember at this point the ObjectPool is full and therefore either an existing object should be returned or
        //no object should be returned.
        DatabaseManager databaseManager4 = DatabaseManager.getInstance(0);

        System.out.println( databaseManager1 );
        System.out.println( databaseManager2 );
        System.out.println( databaseManager3 );
        System.out.println( databaseManager4 );

        //TODO: set the isInUse property of all objects in object pool to false;
        databaseManager1.setAllFree();

        //TODO: create another instance of DatabaseManager:
        //Note: At this point there should be 3 instances of DatabaseManager in the pool and therefore, the getInstance method
        //should randonmly picked one and return it and set its isInUse to yes.
        databaseManager4 = DatabaseManager.getInstance(0);
        System.out.println( databaseManager4 );
    }
}
