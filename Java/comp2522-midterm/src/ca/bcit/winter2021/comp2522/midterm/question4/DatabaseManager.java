package ca.bcit.winter2021.comp2522.midterm.question4;

//TODO: Change this class to support an Object Pool for this class. Checkout the Problem4's description.
public final class DatabaseManager {
    private static ObjectPool objectPool;
    private boolean isInUse;

    private DatabaseManager() {
        isInUse = true;
    }

    public static DatabaseManager getInstance(int maxSize) {
        if ( objectPool == null ){
            if (maxSize <= 0){
                return null;
            }
            objectPool = ObjectPool.getInstance(maxSize);
            return null;
        }

        DatabaseManager databaseManager = objectPool.getFreeObj();
        if (databaseManager != null) {
            databaseManager.setInUse(true);
            return databaseManager;
        }

        if (objectPool.reachMax()) {
            return null;
        }

        databaseManager = new DatabaseManager();
        objectPool.addObject(databaseManager);

        return databaseManager;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public void setAllFree(){
        objectPool.freeAllDatabaseManagers();
    }
}
