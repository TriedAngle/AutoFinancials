package net.strobl.frontend.tabs.dashboard;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.strobl.management.Manager;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    LineChart chartMoneyTime;
    @FXML
    StackedAreaChart chartProjectMoney;

    ObservableList<LineChart.Series> chartMTSerieses;
    ObservableList<StackedAreaChart.Series> chartPMSerieses;

    @FXML
    Label labelCurrentMoney;
    @FXML
    Label labelLastProject;
    @FXML
    Button buttonConnect;
    @FXML
    Label labelCurrMoney;
    @FXML
    Label labelLProject;


    private void setTranslation(){
        labelCurrentMoney.setText(Manager.getLang().getTranslation(Manager.getKeys()[0]));
        labelLastProject.setText(Manager.getLang().getTranslation(Manager.getKeys()[0]));
        labelCurrMoney.setText(Manager.getLang().getTranslation(Manager.getKeys()[2]));
        labelLProject.setText(Manager.getLang().getTranslation(Manager.getKeys()[3]));
        buttonConnect.setText(Manager.getLang().getTranslation(Manager.getKeys()[1]));
        chartMoneyTime.setTitle(Manager.getLang().getTranslation(Manager.getKeys()[4]));
        chartProjectMoney.setTitle(Manager.getLang().getTranslation(Manager.getKeys()[5]));
    }

    public static void update(){

    }

    private void setCharts(){
        chartMoneyTime.getData().setAll(chartMTSerieses);
        chartProjectMoney.getData().setAll(chartMTSerieses);
    }

    public void connectNow(ActionEvent event){
        if(!Manager.getDataManager().getPostgreSQLDataManager().isConnected()){
            Manager.getDataManager().getPostgreSQLDataManager().connectToDataBase();
        }

    }

    private void setLabls(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTranslation();
    }
}
