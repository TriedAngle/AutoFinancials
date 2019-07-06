package net.strobl.frontend.tabs.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import net.strobl.data.json.JSONManager;
import net.strobl.management.Manager;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    Button buttonSaveChanges;
    @FXML
    Button buttonTestConnection;
    @FXML
    Button buttonCreateTable;
    @FXML
    ToggleButton toggleDarkMode;
    @FXML
    TextField textDatabase;
    @FXML
    TextField textUser;
    @FXML
    PasswordField textPassword;
    @FXML
    TextField textCreateTableName;
    @FXML
    Circle circleTestIndicator;

    private String url, username, password, table;


    public boolean testConnection(ActionEvent event) {
        if (checkFields()) {
            Manager.getDataManager().getPostgreSQLDataManager().testConnection(url, username, password);
            if (Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
                Manager.getDataManager().getPostgreSQLDataManager().closeCurrentDataBase();
                circleTestIndicator.setFill(Color.GREEN);
                return true;
            } else {
                circleTestIndicator.setFill(Color.RED);
            }
        }
        return false;
    }

    private boolean checkFields() {
        boolean tmp = true;
        if (textDatabase.getText().equals("")) {
            textDatabase.setText("specify input");
            tmp = false;
        }
        if (textUser.getText().equals("")) {
            textUser.setText("specify input");
            tmp = false;
        }
        if (textPassword.getText().equals("")) {
            textPassword.setText("specify input");
            tmp = false;
        }
        return tmp;
    }

    private void setCredentials() {
        System.out.println(checkFields());
        if (checkFields()) {
            url = textDatabase.getText();
            username = textUser.getText();
            password = textPassword.getText();
            table = "";
            JSONManager.writeCredentials(url, table, username, password);
        }
    }

    public void createTable(ActionEvent event) {

    }

    public void saveAll(ActionEvent event) {
        setCredentials();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        circleTestIndicator.setFill(Color.GRAY);
        textDatabase.setPromptText("Please enter a valid IP Address");
        textUser.setPromptText("Please enter your Database's User");
        textPassword.setPromptText("Enter your password");
    }
}
