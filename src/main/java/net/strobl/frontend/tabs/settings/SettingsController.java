package net.strobl.frontend.tabs.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ToggleButton toggleDarkMode;
    @FXML
    TextField textDatabase;
    @FXML
    TextField textUser;
    @FXML
    PasswordField textPassword;
    @FXML
    Circle circleTestIndicator;
    @FXML
    ComboBox comboSelectLang;
    @FXML
    Label labelDataBase;
    @FXML
    Label labelUser;
    @FXML
    Label labelPassword;

    private String hostname, username, password;
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

    private void setTranslation(){
        labelDataBase.setText(Manager.getLang().getTranslation(Manager.getKeys()[7]));
        labelUser.setText(Manager.getLang().getTranslation(Manager.getKeys()[8]));
        labelPassword.setText(Manager.getLang().getTranslation(Manager.getKeys()[9]));
        comboSelectLang.setPromptText(Manager.getLang().getTranslation(Manager.getKeys()[10]));
        buttonTestConnection.setText(Manager.getLang().getTranslation(Manager.getKeys()[11]));
    }

    private void setCredentials() {
        if (checkFields()) {
            hostname = textDatabase.getText();
            username = textUser.getText();
            password = textPassword.getText();
            JSONManager.writeCredentials(hostname, username, password);
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
        setTranslation();
        if (!JSONManager.isEmpty()) {
            hostname = JSONManager.readCredentials()[0];
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
        ObservableList<String> items = FXCollections.observableArrayList(Manager.getLanguages());
        comboSelectLang.getItems().setAll(items);
    }

    public void comboAction(ActionEvent event){
        String currVal = comboSelectLang.getValue().toString();
        currLangNum = Manager.getLang().getLangNum(currVal);
    }


}
