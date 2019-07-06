package net.strobl.frontend.tabs.bills;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import net.strobl.management.Manager;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddBillController implements Initializable {
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

    private String project;
    private int amount;
    private String orderedBy;
    private String seller;
    private String reason;
    private ObservableList<String> items;
    private String[] itemsString;
    private boolean intake;
    private boolean digital;
    private boolean paid;
    private String dateOfOrder, dateOfReceive, dateOfPayment;

    private void setVarsByInput() {
        project = textProject.getText();
        amount = Integer.valueOf(textAmount.getText());
        orderedBy = textOrderedBy.getText();
        seller = textSeller.getText();
        reason = textReason.getText();
        if (!textItems.getText().equals("")) {
            items = Manager.getBillListProcessor().filterItems(textItems.getText());
            itemsString = new String[items.size()];
            itemsString = items.toArray(itemsString);
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
        if (checkInput()) {
            setVarsByInput();
            Manager.getDataManager().getPostgreSQLDataManager().insertData(Manager.getDataManager().getPostgreSQLDataManager().getLatestID(), project, amount, intake, digital, paid, dateOfOrder, dateOfReceive, dateOfPayment, orderedBy, seller, itemsString, reason);
            Manager.getDataManager().getPostgreSQLData().setAllBills();
        }

    }


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }

    public boolean isIntake() {
        return intake;
    }

    public void setIntake(boolean intake) {
        this.intake = intake;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getDateOfReceive() {
        return dateOfReceive;
    }

    public void setDateOfReceive(String dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateOrdered.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd.MM.yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dateOrdered.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        dateReceived.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd.MM.yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dateReceived.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        datePaid.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd.MM.yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePaid.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }
}


