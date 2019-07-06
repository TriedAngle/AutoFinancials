package net.strobl.frontend.tabs.bills;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.strobl.management.Manager;
import net.strobl.processing.Bill;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBillController implements Initializable {

    @FXML
    Button buttonSubmit;

    @FXML
    TextField textProject;
    @FXML
    TextField textAmount;
    @FXML
    TextField textOrderedBy;
    @FXML
    TextField textSeller;
    @FXML
    TextField textReason;
    @FXML
    TextArea textItems;
    @FXML
    RadioButton buttonIntake;
    @FXML
    RadioButton buttonDigital;
    @FXML
    RadioButton buttonPaid;
    @FXML
    DatePicker dateOrdered;
    @FXML
    DatePicker dateReceived;
    @FXML
    DatePicker datePaid;

    private Bill selectedbill;
    private int billID;
    private String project;
    private int amount;
    private String orderedBy;
    private String seller;
    private String reason;
    private String items;
    private boolean intake;
    private boolean digital;
    private boolean paid;
    private String dateOfOrder, dateOfReceive, dateOfPayment;
    private String[] itemsString;

    public EditBillController() {
        billID = BillsController.getSelectedBillID();
        selectedbill = Manager.getDataManager().getPostgreSQLDataManager().getSingleBill(billID);
    }

    private void setContent(Bill bill) {
        project = bill.getProject();
        amount = bill.getAmountInCent();
        orderedBy = bill.getOrderedBy();
        seller = bill.getSeller();
        reason = bill.getReason();
        items= "";
        StringBuilder itemBuilder = new StringBuilder();
        for(String item : bill.getItems()){
            itemBuilder.append(item);
            itemBuilder.append(System.lineSeparator());
        }
        items = itemBuilder.toString();
        intake = bill.isIsIntake();
        digital = bill.isIsDigital();
        paid = bill.isIsPaid();
        dateOfOrder = bill.getDateOfOrder();
        dateOfReceive = bill.getDateOfReceive();
        dateOfPayment = bill.getDateOfPayment();

        textProject.setText(project);
        textAmount.setText(String.valueOf(amount));
        textOrderedBy.setText(orderedBy);
        textSeller.setText(seller);
        textReason.setText(reason);
        textItems.setText(items);
        buttonIntake.setSelected(intake);
        buttonDigital.setSelected(digital);
        buttonPaid.setSelected(paid);

        dateOrdered.getEditor().setText(dateOfOrder);
        dateReceived.getEditor().setText(dateOfReceive);
        datePaid.getEditor().setText(dateOfPayment);
    }

    private void setVarsByInput() {
        project = textProject.getText();
        amount = Integer.valueOf(textAmount.getText());
        orderedBy = textOrderedBy.getText();
        seller = textSeller.getText();
        reason = textReason.getText();
        if (!textItems.getText().equals("")) {
            ObservableList<String> itemList = Manager.getBillListProcessor().filterItems(textItems.getText());
            itemsString = new String[itemList.size()];
            itemsString = itemList.toArray(itemsString);
        } else {
            itemsString = new String[0];
        }

        intake = buttonIntake.isSelected();
        digital = buttonDigital.isSelected();
        paid = buttonPaid.isSelected();
        dateOfOrder = dateOrdered.getEditor().getText();
        dateOfReceive = dateReceived.getEditor().getText();
        dateOfPayment = datePaid.getEditor().getText();

    }

    private boolean checkInput() {
        boolean tmp = true;
        if (textProject.getText().equals("")) {
            textProject.setText("specify input");
            tmp = false;
        }
        if (textAmount.getText().equals("")) {
            textAmount.setText("specify input");
            tmp = false;
        }
        try {
            int checkInt = Integer.parseInt(textAmount.getText());
        } catch (NumberFormatException | NullPointerException exception) {
            textAmount.setText("Not a Number");
            tmp = false;
        }
        if (textOrderedBy.getText().equals("")) {
            textOrderedBy.setText("specify input");
            tmp = false;
        }
        if (textSeller.getText().equals("")) {
            textSeller.setText("specify input");
            tmp = false;
        }
        if (dateOrdered.getEditor().getText().equals("")) {
            dateOrdered.getEditor().setText("specify input");
            tmp = false;
        }

        if (tmp) {
            return true;
        }
        return false;
    }

    public void submitBill(ActionEvent event) {
        if(checkInput()){
            setVarsByInput();
            Manager.getDataManager().getPostgreSQLDataManager().updateBill(billID, project, amount, intake, digital, paid, dateOfOrder, dateOfReceive, dateOfPayment, orderedBy, seller, itemsString, reason);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContent(selectedbill);
    }
}
