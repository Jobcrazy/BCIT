package ca.bcit.winter2021.comp2522.midterm.question2;

public class TestQuestion2 {
    public static void runAllTestScenariosForQuestion2() {

        //Note: Do not make changes to the following statement
        //Note: Do not make changes to the following statement
        TransitMap tm = new TransitMap();

        //TODO: Create instances to represent the roads (cities, distances) of the sample transit map.
        tm.addRoad(new Road(105f, new String[]{"Chicago", "Detroit"}));
        tm.addRoad(new Road(151f, new String[]{"Chicago", "New york"}));
        tm.addRoad(new Road(70f, new String[]{"New york", "Boston"}));
        tm.addRoad(new Road(30f, new String[]{"Boston", "Detroit"}));
        tm.addRoad(new Road(230f, new String[]{"Detroit", "New york"}));

        //TODO: add anything else needed here (if needed)

        //Note: Do not make changes to the following statements
        //Note: Do not make changes to the following statements
        testGetDistance(tm, "Boston");
        testGetBiggestDistance(tm);
        testSortTransitMap(tm);
    }

    private static void testGetDistance(TransitMap tm, String cityName) {
        tm.getDistances(cityName);
    }

    private static void testGetBiggestDistance(TransitMap tm) {
        TransitMap.getBiggestDistance(tm);
    }

    public static void testSortTransitMap(TransitMap tm) {
        tm.sortRoadsDesc();
    }
}
