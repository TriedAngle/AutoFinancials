package net.strobl.management;

import net.strobl.data.csv.CSVData;
import net.strobl.data.csv.CSVDataManager;
import net.strobl.data.json.JSONManager;
import net.strobl.data.postgresql.PostgreSQLData;
import net.strobl.data.postgresql.PostgreSQLDataManager;
import net.strobl.processing.Bill;

public class DataManager {
    private Manager manager;
    private net.strobl.data.csv.CSVData CSVData;
    private CSVDataManager csvDataManager;
    private PostgreSQLDataManager postgreSQLDataManager;
    private PostgreSQLData postgreSQLData;


    public DataManager(Manager manager) {
        this.manager = manager;
        postgreSQLData = new PostgreSQLData();
        postgreSQLDataManager = new PostgreSQLDataManager();
        postgreSQLDataManager.setCredentialsWithJSON();

        if(!JSONManager.isEmpty()) {
            postgreSQLDataManager.connectToDataBase();
        }
    }

    public PostgreSQLData getPostgreSQLData() {
        return postgreSQLData;
    }

    public PostgreSQLDataManager getPostgreSQLDataManager() {
        return postgreSQLDataManager;
    }

    public net.strobl.data.csv.CSVData getCSVData() {
        return CSVData;
    }

    public CSVDataManager getCsvDataManager() {
        return csvDataManager;
    }

    public void setCSVData() {
        CSVData.setAllCSVBills();
    }

}
