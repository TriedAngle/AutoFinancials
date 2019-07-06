package net.strobl.frontend.tabs.dashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    LineChart dataTestChart;

/*
    public void addPrice() {
        XYChart.Series series = new XYChart.Series();
        ObservableList<Bill> tmp = Manager.getDataManager().getPostgreSQLData().getAllBillsNoUnpaid();
        double current = 0;
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).isIsIntake() && tmp.get(i).isIsPaid()) {
                current += ((double) tmp.get(i).getAmountInCent()) / 100;
            } else if (!tmp.get(i).isIsIntake() && tmp.get(i).isIsPaid()) {
                current -= ((double) tmp.get(i).getAmountInCent()) / 100;
            }

            series.getData().add(new XYChart.Data(String.valueOf(i), current));
        }
        dataTestChart.getData().add(series);
    }
*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //addPrice();
    }
}
