package ca.bcit.winter2021.comp2522.midterm.question4;

import java.util.ArrayList;

public class ObjectPool {
    private static ObjectPool objectPool = null;
    private ArrayList<DatabaseManager> databaseManagers = new ArrayList<>();
    private int maxSize;

    private ObjectPool() {
    }

    public static ObjectPool getInstance(int maxSize) {
        if (null == objectPool) {
            objectPool = new ObjectPool();
            objectPool.maxSize = maxSize;
        }
        return objectPool;
    }

    public boolean addObject(DatabaseManager databaseManager) {
        if (databaseManagers.size() >= maxSize) {
            return false;
        }
        databaseManagers.add(databaseManager);
        return true;
    }

    public boolean reachMax() {
        return databaseManagers.size() >= maxSize;
    }

    public DatabaseManager getFreeObj() {
        for (DatabaseManager databaseManager : databaseManagers) {
            if (!databaseManager.isInUse()) {
                return databaseManager;
            }
        }

        return null;
    }

    public void freeAllDatabaseManagers(){
        for (DatabaseManager databaseManager : databaseManagers) {
            databaseManager.setInUse(false);
        }
    }
}
