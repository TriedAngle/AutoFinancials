package net.strobl.management;

import net.strobl.data.csv.CSVData;
import net.strobl.data.csv.CSVDataManager;
import net.strobl.data.json.JSONManager;
import net.strobl.data.postgresql.PostgreSQLData;
import net.strobl.data.postgresql.PostgreSQLDataManager;

public class DataManager {
    private Manager manager;
    private net.strobl.data.csv.CSVData CSVData;
    private CSVDataManager csvDataManager;
    private PostgreSQLDataManager postgreSQLDataManager;
    private PostgreSQLData postgreSQLData;


    public DataManager(Manager manager) {
        this.manager = manager;
        CSVData = new CSVData();
        csvDataManager = new CSVDataManager();
        postgreSQLData = new PostgreSQLData();
        postgreSQLDataManager = new PostgreSQLDataManager();
        if(!JSONManager.isEmpty()) {
            postgreSQLDataManager.setCredentialsWithJSON();
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
