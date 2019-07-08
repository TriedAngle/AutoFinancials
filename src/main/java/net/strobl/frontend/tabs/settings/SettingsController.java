package net.strobl.frontend.tabs.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
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
    @FXML
    ComboBox comboSelectLang;

    private String hostname, username, password, table;
    private int currLangNum = 0;
    private boolean darkmode = false;


    public boolean testConnection(ActionEvent event) {
        if (checkFields()) {
            Manager.getDataManager().getPostgreSQLDataManager().testConnection(hostname, username, password);
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
        if (checkFields()) {
            hostname = textDatabase.getText();
            username = textUser.getText();
            password = textPassword.getText();
            table = "";
            JSONManager.writeCredentials(hostname, table, username, password);
            Manager.getDataManager().getPostgreSQLDataManager().setCredentialsWithJSON();

            if (Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
                Manager.getDataManager().getPostgreSQLDataManager().closeCurrentDataBase();
            }
            if (!Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
                Manager.getDataManager().getPostgreSQLDataManager().connectToDataBase();
            }

        }

    }

    public void createTable(ActionEvent event) {

    }

    public void saveAll(ActionEvent event) {
        Manager.getJSON().writeSettings(currLangNum, darkmode);
        setCredentials();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!JSONManager.isEmpty()) {
            hostname = JSONManager.readCredentials()[0];
            table = JSONManager.readCredentials()[1];
            username = JSONManager.readCredentials()[2];
            password = JSONManager.readCredentials()[3];
            textDatabase.setText(hostname);
            textUser.setText(username);
            textPassword.setText(password);
        }
        if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()){
            circleTestIndicator.setFill(Color.GREEN);
        }else {
            circleTestIndicator.setFill(Color.RED);
        }
        textDatabase.setPromptText("Please enter a valid IP Address");
        textUser.setPromptText("Please enter your Database's User");
        textPassword.setPromptText("Enter your password");
        comboSelectLang.getItems().setAll(Manager.getLanguages());
    }

    public void comboAction(ActionEvent event){
        String currVal = comboSelectLang.getValue().toString();
        currLangNum = Manager.getLang().getLangNum(currVal);
    }


}
