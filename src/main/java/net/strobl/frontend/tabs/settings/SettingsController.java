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
    TextField textPassword;
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
            if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()){
                circleTestIndicator.setFill(Color.GREEN);
            }
                return true;
            } else {
                circleTestIndicator.setFill(Color.RED);
            }

        return false;
    }

    private boolean checkFields() {
        boolean tmp = true;
        if (hostname.equals("")) {
            textDatabase.setPromptText("specify input");
            tmp = false;
        }
        if (username.equals("")) {
            textUser.setPromptText("specify input");
            tmp = false;
        }
        if (password.equals("")) {
            textPassword.setPromptText("specify input");
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
        hostname = textDatabase.getText();
        username = textUser.getText();
        password = textPassword.getText();
        if (checkFields()) {
            Manager.getJSON().writeCredentials(hostname, username, password);
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
            hostname = Manager.getJSON().readCredentials()[0];
            username = Manager.getJSON().readCredentials()[1];
            password = Manager.getJSON().readCredentials()[2];
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
