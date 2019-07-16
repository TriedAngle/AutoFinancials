package net.strobl.data.postgresql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.strobl.management.Manager;
import net.strobl.processing.Bill;

import java.util.List;

public class PostgreSQLData {
    private ObservableList<Bill> allBills;
    private ObservableList<Bill> allBillsNoUnpaid;
    private List<String> projectNames;
    private ObservableList<Bill> separatedBills;


    public void setAllBills() {
        allBills = FXCollections.observableList(Manager.getDataManager().getPostgreSQLDataManager().getAllBills());
    }

    public void setSeparatedBills(String filterName, Boolean showUnpaid) {
    }

    public void setProjectNames() {
        projectNames = null;
        projectNames = Manager.getDataManager().getPostgreSQLDataManager().getProjectNames();
        projectNames.add("All");
    }

    public ObservableList<Bill> getSeparatedBills() {
        return separatedBills;
    }

    public ObservableList<Bill> getAllBills() {
        return allBills;
    }

    public ObservableList<Bill> getAllBillsNoUnpaid() {
        return allBillsNoUnpaid;
    }

    public List<String> getProjectNames() {
        return projectNames;
    }

}
