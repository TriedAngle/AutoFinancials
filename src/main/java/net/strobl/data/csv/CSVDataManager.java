package net.strobl.data.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.strobl.processing.Bill;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVDataManager {
    private ObservableList<Bill> allCSVBills = FXCollections.observableArrayList();

    public void readData(String path) throws IOException {
        allCSVBills = FXCollections.observableArrayList();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

        List<String[]> allData = reader.readAll();

        for (String[] data : allData) {
            if (data.length > 1) {
                SimpleIntegerProperty billID = new SimpleIntegerProperty();
                SimpleStringProperty project = new SimpleStringProperty(data[0]);
                SimpleIntegerProperty amountInCent = new SimpleIntegerProperty(Integer.valueOf(data[2]));
                SimpleBooleanProperty isIntake = new SimpleBooleanProperty(Boolean.valueOf(data[1]));
                SimpleBooleanProperty isDigital = new SimpleBooleanProperty(Boolean.valueOf(data[3]));
                SimpleBooleanProperty isPaid = new SimpleBooleanProperty(Boolean.valueOf(data[10]));
                SimpleStringProperty dateOfOrder =  new SimpleStringProperty(data[4]);
                SimpleStringProperty dateOfReceive = new SimpleStringProperty(data[5]);
                SimpleStringProperty dateOfPayment = new SimpleStringProperty(data[6]);
                SimpleStringProperty orderedBy = new SimpleStringProperty(data[9]);
                SimpleStringProperty seller = new SimpleStringProperty(data[7]);
                ObservableList<String> items = FXCollections.observableArrayList();
                SimpleStringProperty reason = new SimpleStringProperty(data[8]);

                allCSVBills.add(new Bill(billID, project, amountInCent, isIntake, isDigital, isPaid, dateOfOrder, dateOfReceive, dateOfPayment,reason, orderedBy, seller, items));
            }
        }
    }

    public ObservableList<Bill> getAllCSVBills() {
        return allCSVBills;
    }
}
