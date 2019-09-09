package net.strobl.data.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.strobl.processing.Bill;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVDataManager {

  private ObservableList<Bill> allCSVBills = FXCollections.observableArrayList();

  public void readData() {
    String path = "src/main/java/net/strobl/data/csv/bckpdta.csv";
    List<String[]> allData = new ArrayList<>();
    allCSVBills = FXCollections.observableArrayList();
    try {
      FileReader fileReader = new FileReader(path);
      System.out.println(fileReader);
      CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
      allData = reader.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (String[] data : allData) {
      if (data.length > 1) {
        SimpleIntegerProperty billID = new SimpleIntegerProperty(Integer.parseInt(data[0]));
        SimpleStringProperty project = new SimpleStringProperty(data[1]);
        SimpleIntegerProperty amountInCent = new SimpleIntegerProperty(Integer.parseInt(data[2]));
        SimpleBooleanProperty isIntake = new SimpleBooleanProperty(Boolean.parseBoolean(data[3]));
        SimpleBooleanProperty isDigital = new SimpleBooleanProperty(Boolean.parseBoolean(data[4]));
        SimpleBooleanProperty isPaid = new SimpleBooleanProperty(Boolean.parseBoolean(data[5]));
        SimpleStringProperty dateOfOrder = new SimpleStringProperty(data[6]);
        SimpleStringProperty dateOfReceive = new SimpleStringProperty(data[7]);
        SimpleStringProperty dateOfPayment = new SimpleStringProperty(data[8]);
        SimpleStringProperty orderedBy = new SimpleStringProperty(data[9]);
        SimpleStringProperty seller = new SimpleStringProperty(data[10]);
        String itemsAsString = data[11];
        String[] sepItems = itemsAsString.split(",");
        ObservableList<String> items = FXCollections.observableArrayList(sepItems);
        SimpleStringProperty reason = new SimpleStringProperty(data[12]);

        allCSVBills.add(
            new Bill(billID, project, amountInCent, isIntake, isDigital, isPaid, dateOfOrder,
                dateOfReceive, dateOfPayment, reason, orderedBy, seller, items));
      }
    }
      System.out.println(getAllCSVBills());
  }

  public ObservableList<Bill> getAllCSVBills() {
    return allCSVBills;
  }
}
