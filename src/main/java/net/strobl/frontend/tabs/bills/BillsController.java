package net.strobl.frontend.tabs.bills;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.strobl.management.Manager;
import net.strobl.processing.Bill;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

public class BillsController implements Initializable {

    @FXML
    TableView<Bill> displayBills;
    @FXML
    TableColumn<Bill, Integer> columnBillID;
    @FXML
    TableColumn<Bill, Integer> columnAmount;
    @FXML
    TableColumn<Bill, String> columnDateOfOrder;
    @FXML
    TableColumn<Bill, String> columnReceivedOn;
    @FXML
    TableColumn<Bill, String> columnDateOfPayment;
    @FXML
    TableColumn<Bill, String> columnOrderedBy;
    @FXML
    TableColumn<Bill, String> columnReason;
    @FXML
    TableColumn<Bill, String> columnSeller;
    @FXML
    TableColumn<Bill, String> columnProducts;
    @FXML
    TableColumn<Bill, String> columnProjects;
    @FXML
    TableColumn<Bill, Boolean> columnPaid;

    @FXML
    TableColumn<Bill, Boolean> columnDigital;

    @FXML
    MenuButton buttonSelectProject;

    @FXML
    Label labelMoneySpent;
    @FXML
    Label labelMoneyGained;
    @FXML
    Label labelTotalRevenue;

    @FXML
    Button buttonNewBill;

    @FXML
    Button buttonDeleteBill;

    @FXML
    TextField textDeleteBill;

    private static Timer timer;

    private List<String> menuItemNames = new ArrayList<>();

    private Boolean showUnpaid = true;
    private String currentText = "";
    private Boolean initialized = false;
    public static Boolean isStopped = false;

    private static int selectedBillID;

    private ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();


    private void generateMenuButtonItems() {
        menuItemNames = new ArrayList<>();
        menuItems = FXCollections.observableArrayList();
        if (Manager.getDataManager().getPostgreSQLDataManager().isConnected() && Manager.getDataManager().getPostgreSQLDataManager().getAllBills() != null) {
            for (String name : Manager.getDataManager().getPostgreSQLDataManager().getProjectNames()) {
                if (!menuItemNames.contains(name)) {
                    menuItems.add(new MenuItem(name));
                    menuItemNames.add(name);
                }
            }

            for (MenuItem item : menuItems) {
                EventHandler<ActionEvent> event = e -> selectButtonActions(menuItems.indexOf(item));
                item.setOnAction(event);
            }
            buttonSelectProject.getItems().setAll(menuItems);
            if (!initialized) {
                buttonSelectProject.setText("All");
                currentText = "All";
                initialized = true;
            }
        }
    }

    private void selectButtonActions(int index) {
        if (Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
            for (String name : Manager.getDataManager().getPostgreSQLDataManager().getProjectNames()) {
                if (name.equals(menuItems.get(index).getText())) {
                    displayBills.setItems(Manager.getDataManager().getPostgreSQLDataManager().fetchFilteredBills(name, showUnpaid));
                    currentText = name;
                    buttonSelectProject.setText(name);
                    labelMoneySpent.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, showUnpaid)[0]) / 100));
                    labelMoneyGained.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, showUnpaid)[1]) / 100));
                    labelTotalRevenue.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, showUnpaid)[2]) / 100));
                }
            }
        }
    }


    public void toggleShowUnpaid(ActionEvent event) {
        if (Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
            showUnpaid = !showUnpaid;
            displayBills.setItems(Manager.getDataManager().getPostgreSQLDataManager().fetchFilteredBills(currentText, showUnpaid));
            System.out.println(currentText + showUnpaid);
            refresh();
            labelMoneySpent.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, showUnpaid)[0]) / 100));
            labelMoneyGained.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, showUnpaid)[1]) / 100));
            labelTotalRevenue.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, showUnpaid)[2]) / 100));

        }

    }

    private void populateBills() {
        displayBills.setItems(Manager.getDataManager().getPostgreSQLDataManager().getAllBills());
        columnBillID.setCellValueFactory(new PropertyValueFactory<>("billID"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("realAmount"));
        columnDateOfOrder.setCellValueFactory(new PropertyValueFactory<>("dateOfOrder"));
        columnReceivedOn.setCellValueFactory(new PropertyValueFactory<>("dateOfReceive"));
        columnDateOfPayment.setCellValueFactory(new PropertyValueFactory<>("dateOfPayment"));
        columnOrderedBy.setCellValueFactory(new PropertyValueFactory<>("orderedBy"));
        columnReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        columnSeller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        columnProducts.setCellValueFactory(new PropertyValueFactory<>("items"));
        columnPaid.setCellValueFactory(new PropertyValueFactory<>("isPaid"));
        columnDigital.setCellValueFactory(new PropertyValueFactory<>("isDigital"));
        columnProjects.setCellValueFactory(new PropertyValueFactory<>("project"));
        displayBills.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                selectedBillID = displayBills.getSelectionModel().getSelectedItem().getBillID();
                showEditBillMenu();
            }
        });
    }

    public void showAddBillMenu(ActionEvent event) {
        if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/bills/AddBill.fxml"));
                Stage addBillStage = new Stage();
                addBillStage.setTitle("Create a new Bill");
                addBillStage.setScene(new Scene(root));
                addBillStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showEditBillMenu() {
        if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/tabs/bills/EditBill.fxml"));
                Stage editBillStage = new Stage();
                editBillStage.setTitle("Edit Bill");
                editBillStage.setScene(new Scene(root));
                editBillStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBill(ActionEvent event) {
        if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
            boolean tmp = true;
            try {
                int checkInt = Integer.parseInt(textDeleteBill.getText());
            } catch (NumberFormatException | NullPointerException exception) {
                textDeleteBill.setText("");
                textDeleteBill.setPromptText("Not a Number");
                tmp = false;
            }
            if (tmp) {
                int index = Integer.valueOf(textDeleteBill.getText());
                if (!(index > Manager.getDataManager().getPostgreSQLDataManager().getAllBills().size()) || !(index <= 0)) {
                    Manager.getDataManager().getPostgreSQLDataManager().deleteRow(index);
                } else {
                    textDeleteBill.setText("");
                    textDeleteBill.setPromptText("Number is not valid");
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()) {
            displayBills.setItems(Manager.getDataManager().getPostgreSQLDataManager().getAllBills());
            if(Manager.getDataManager().getPostgreSQLDataManager().getAllBills() != null){
                labelMoneySpent.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, true)[0]) / 100));
                labelMoneyGained.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, true)[1]) / 100));
                labelTotalRevenue.setText(String.valueOf(((double) Manager.getDataManager().getPostgreSQLDataManager().getMoney(currentText, true)[2]) / 100));
            }
            populateBills();
            generateMenuButtonItems();
        }

//        startTimer();
    }

    public void refresh() {
        generateMenuButtonItems();
        populateBills();
    }

//    private TimerTask timerTask = new TimerTask() {
//        @Override
//        public void run() {
//            refresh();
//        }
//    };

    public static int getSelectedBillID() {
        return selectedBillID;
    }

//    public void startTimer() {
//        timer = new Timer();
//        timer.schedule(timerTask, 10);
//    }

//    public static void stopTimer() {
//        timer.cancel();
//        timer.purge();
//    }

}
