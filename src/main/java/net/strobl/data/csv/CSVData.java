package net.strobl.data.csv;

import javafx.collections.ObservableList;
import net.strobl.management.Manager;
import net.strobl.processing.Bill;

import java.io.IOException;
import java.util.List;

public class CSVData {
    private ObservableList<Bill> allBills;
    private List<String> projectNames;
    private ObservableList<ObservableList<Bill>> separatedBills;



    public void setAllCSVBills(){
        Manager.getDataManager().getCsvDataManager().readData();
        allBills = Manager.getDataManager().getCsvDataManager().getAllCSVBills();
        setProjectNames();
        setSeparatedBills();
    }

    public void setProjectNames(){
        projectNames = Manager.getBillListProcessor().getProjectNames();
    }

    public void setSeparatedBills(){
        separatedBills = Manager.getBillListProcessor().generateSeperateBills();
    }

    public ObservableList<ObservableList<Bill>> getSeparatedBills(){
        return separatedBills;
    }

    public List<String> getProjectNames(){
        return projectNames;
    }

    public ObservableList<Bill> getAllCSVBills(){
        return allBills;
    }
}
