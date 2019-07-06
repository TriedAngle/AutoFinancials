package net.strobl.management;

import net.strobl.processing.BillListProcessor;

import java.io.IOException;

public class Manager {
    private static App app;
    private static DataManager dataManager;
    private static BillListProcessor billListProcessor;

    public Manager(App app) throws IOException {
        Manager.app = app;
        dataManager = new DataManager(this);
        billListProcessor = new BillListProcessor();
    }

    public static App getApp() {
        return app;
    }
    public static DataManager getDataManager(){
        return dataManager;
    }
    public static BillListProcessor getBillListProcessor(){
        return billListProcessor;
    }

}
