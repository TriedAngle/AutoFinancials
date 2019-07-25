package net.strobl.frontend.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.strobl.management.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button buttonDashboard;
    @FXML
    private Button buttonBills;
    @FXML
    private Button buttonProjects;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonExit;

    @FXML
    private AnchorPane anchorPaneDisplay;
    @FXML
    private Label labelCurrentTab;

    private Parent viewDashboard;
    private Parent viewBills;
    private Parent viewProjects;
    private Parent viewTechvintory;
    private Parent viewSettings;

    public void loadDashboard(ActionEvent event) throws IOException {
        anchorPaneDisplay.getChildren().removeAll();
        if (viewDashboard != null) {
            anchorPaneDisplay.getChildren().setAll(viewDashboard);
        } else {
            viewDashboard = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/dashboard/Dashboard.fxml"));
            anchorPaneDisplay.getChildren().setAll(viewDashboard);
        }

        labelCurrentTab.setText("Dashboard");
    }

    public void loadTechvintory(ActionEvent event) throws IOException{
        anchorPaneDisplay.getChildren().removeAll();
        if (viewTechvintory != null){
            anchorPaneDisplay.getChildren().setAll(viewTechvintory);
        }else {
            viewTechvintory = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/techvintory/Techvintory.fxml"));
            anchorPaneDisplay.getChildren().setAll(viewTechvintory);
        }
    }

    public void loadBills(ActionEvent event) throws IOException {
        anchorPaneDisplay.getChildren().removeAll();
        if (viewBills != null) {
            anchorPaneDisplay.getChildren().setAll(viewBills);
        } else {
            viewBills = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/bills/Bills.fxml"));
            anchorPaneDisplay.getChildren().setAll(viewBills);
        }

        labelCurrentTab.setText("Bills");
    }

    public void loadProjects(ActionEvent event) throws IOException {
        anchorPaneDisplay.getChildren().removeAll();
        if (viewProjects != null) {
            anchorPaneDisplay.getChildren().setAll(viewProjects);
        } else {
            viewProjects = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/projects/Projects.fxml"));
            anchorPaneDisplay.getChildren().setAll(viewProjects);
        }

        labelCurrentTab.setText("Projects");
    }

    public void loadSettings(ActionEvent event) throws IOException {
        anchorPaneDisplay.getChildren().removeAll();
        if (viewSettings != null) {
            anchorPaneDisplay.getChildren().setAll(viewSettings);
        } else {
            viewSettings = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/settings/Settings.fxml"));
            anchorPaneDisplay.getChildren().setAll(viewSettings);
        }

        labelCurrentTab.setText("Settings");
    }

    public void exit(ActionEvent event) {
//        BillsController.stopTimer();
        App.exit();
    }

    public void activateBillButton() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/dashboard/Dashboard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (viewDashboard != null) {
            anchorPaneDisplay.getChildren().setAll(viewDashboard);
        } else {
            viewDashboard = fxml;
            anchorPaneDisplay.getChildren().setAll(viewDashboard);
        }

        labelCurrentTab.setText("Dashboard");
    }
}
