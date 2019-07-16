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
    private int spent, gained, revenue;
    private String currentFilterName;
    private Boolean currentShowUnpaid;
    private String currentCalcName;
    private boolean currentCalcUnpaid;

    public void setMoney(String name, boolean calcUnpaid) {
        spent = 0;
        gained = 0;
        revenue = 0;
        currentCalcName = name;
        currentCalcUnpaid = calcUnpaid;
        if (calcUnpaid) {
            if (name.equals("All") || name.equals("")) {
                for (Bill bill : getAllBills()) {
                    if (!bill.isIsIntake()) {
                        spent += bill.getAmountInCent();
                    }
                    if (bill.isIsIntake()) {
                        gained += bill.getAmountInCent();
                    }
                }
            } else {
                for (Bill bill : separatedBills) {
                    if (!bill.isIsIntake()) {
                        spent += bill.getAmountInCent();
                    }
                    if (bill.isIsIntake()) {
                        gained += bill.getAmountInCent();
                    }
                }
            }
        }
        if (!calcUnpaid) {
            if (name.equals("All") || name.equals("")) {
                for (Bill bill : getAllBills()) {
                    if (!bill.isIsIntake() && bill.isIsPaid()) {
                        spent += bill.getAmountInCent();
                    }
                    if (bill.isIsIntake() && bill.isIsPaid()) {
                        gained += bill.getAmountInCent();
                    }
                }
            } else {
                for (Bill bill : separatedBills) {
                    if (!bill.isIsIntake() && bill.isIsPaid()) {
                        spent += bill.getAmountInCent();
                    }
                    if (bill.isIsIntake() && bill.isIsPaid()) {
                        gained += bill.getAmountInCent();
                    }
                }
            }
        }
        revenue = gained - spent;
    }

    public void setAllBills() {
        allBills = FXCollections.observableList(Manager.getDataManager().getPostgreSQLDataManager().getAllBills());
    }

    public void setSeparatedBills(String filterName, Boolean showUnpaid) {
        separatedBills = null;
        separatedBills = Manager.getDataManager().getPostgreSQLDataManager().fetchFilteredBills(filterName, showUnpaid);
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


    public int getSpent() {
        return spent;
    }

    public int getGained() {
        return gained;
    }

    public int getRevenue() {
        return revenue;
    }
}
