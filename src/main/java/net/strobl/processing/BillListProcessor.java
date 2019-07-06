package net.strobl.processing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.strobl.management.Manager;

import java.util.ArrayList;
import java.util.List;

public class BillListProcessor {
    public List<String> getProjectNames() {
        ObservableList<Bill> allBills = Manager.getDataManager().getCSVData().getAllCSVBills();
        List<String> projectNames = new ArrayList<>();
        for (Bill bill : allBills) {
            if (!projectNames.contains(bill.getProject())) {
                projectNames.add(bill.getProject());
            }
        }
        projectNames.add("All");
        return projectNames;
    }

    public ObservableList<ObservableList<Bill>> generateSeperateBills() {
        ObservableList<Bill> pList;
        ObservableList<ObservableList<Bill>> separatedBills = FXCollections.observableArrayList();

        for (String name : Manager.getDataManager().getCSVData().getProjectNames()) {
            pList = FXCollections.observableArrayList();
            for (Bill bill : Manager.getDataManager().getCSVData().getAllCSVBills()) {
                if (bill.getProject().equals(name)) {
                    pList.add(bill);
                }
            }
            separatedBills.add(pList);
            separatedBills.add(Manager.getDataManager().getCSVData().getAllCSVBills());
        }
        return separatedBills;
    }

    public ObservableList<String> filterItems(String unfilteredItems){
        if(unfilteredItems.equals("")){
            return null;
        }
        String[] splittedItems = unfilteredItems.split("[\r\n]+");
        ObservableList<String> items = FXCollections.observableArrayList(splittedItems);
        return items;
    }
}
