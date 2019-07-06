package net.strobl.processing;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Bill implements Comparable<Bill> {
    private SimpleIntegerProperty billID;
    private SimpleStringProperty project;
    private SimpleIntegerProperty amountInCent;
    private SimpleDoubleProperty amount;
    private SimpleBooleanProperty isIntake;
    private SimpleBooleanProperty isDigital;
    private SimpleBooleanProperty isPaid;
    private SimpleStringProperty dateOfOrder;
    private SimpleStringProperty dateOfReceive;
    private SimpleStringProperty dateOfPayment;
    private SimpleStringProperty orderedBy;
    private SimpleStringProperty seller;
    private ObservableList<String> items;
    private SimpleStringProperty reason;
    private SimpleDoubleProperty realAmount;

    public Bill(SimpleIntegerProperty billID, SimpleStringProperty project, SimpleIntegerProperty amountInCent,
                SimpleBooleanProperty isIntake, SimpleBooleanProperty isDigital, SimpleBooleanProperty isPaid,
                SimpleStringProperty dateOfOrder, SimpleStringProperty dateOfReceive, SimpleStringProperty dateOfPayment,
                SimpleStringProperty reason, SimpleStringProperty orderedBy, SimpleStringProperty seller,
                ObservableList<String> items) {

        this.billID = billID;
        this.project = project;
        this.amountInCent = amountInCent;
        this.isIntake = isIntake;
        this.isDigital = isDigital;
        this.isPaid = isPaid;
        this.dateOfOrder = dateOfOrder;
        this.dateOfReceive = dateOfReceive;
        this.dateOfPayment = dateOfPayment;
        this.orderedBy = orderedBy;
        this.reason = reason;
        this.seller = seller;
        this.items = items;
        Double temp = (double) getAmountInCent();
        temp /= 100;
        amount = new SimpleDoubleProperty(temp);
        if(isIntake.get()){
            realAmount = new SimpleDoubleProperty(amount.get());
        }else {
            realAmount = new SimpleDoubleProperty(-amount.get());
        }


    }

    public int getBillID() {
        return billID.get();
    }

    public SimpleIntegerProperty billIDProperty() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID.set(billID);
    }

    public String getProject() {
        return project.get();
    }

    public SimpleStringProperty projectProperty() {
        return project;
    }

    public void setProject(String project) {
        this.project.set(project);
    }

    public int getAmountInCent() {
        return amountInCent.get();
    }

    public SimpleIntegerProperty amountInCentProperty() {
        return amountInCent;
    }

    public void setAmountInCent(int amountInCent) {
        this.amountInCent.set(amountInCent);
    }

    public boolean isIsIntake() {
        return isIntake.get();
    }

    public SimpleBooleanProperty isIntakeProperty() {
        return isIntake;
    }

    public void setIsIntake(boolean isIntake) {
        this.isIntake.set(isIntake);
    }

    public boolean isIsDigital() {
        return isDigital.get();
    }

    public SimpleBooleanProperty isDigitalProperty() {
        return isDigital;
    }

    public void setIsDigital(boolean isDigital) {
        this.isDigital.set(isDigital);
    }

    public boolean isIsPaid() {
        return isPaid.get();
    }

    public SimpleBooleanProperty isPaidProperty() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid.set(isPaid);
    }

    public String getDateOfOrder() {
        return dateOfOrder.get();
    }

    public SimpleStringProperty dateOfOrderProperty() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder.set(dateOfOrder);
    }

    public String getDateOfReceive() {
        return dateOfReceive.get();
    }

    public SimpleStringProperty dateOfReceiveProperty() {
        return dateOfReceive;
    }

    public void setDateOfRecieve(String dateOfRecieve) {
        this.dateOfReceive.set(dateOfRecieve);
    }

    public String getDateOfPayment() {
        return dateOfPayment.get();
    }

    public SimpleStringProperty dateOfPaymentProperty() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment.set(dateOfPayment);
    }

    public String getOrderedBy() {
        return orderedBy.get();
    }

    public SimpleStringProperty orderedByProperty() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy.set(orderedBy);
    }

    public String getSeller() {
        return seller.get();
    }

    public SimpleStringProperty sellerProperty() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller.set(seller);
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }

    public String getReason() {
        return reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public void setDateOfReceive(String dateOfReceive) {
        this.dateOfReceive.set(dateOfReceive);
    }

    public double getRealAmount() {
        return realAmount.get();
    }

    public SimpleDoubleProperty realAmountProperty() {
        return realAmount;
    }

    public void setRealAmount(double realAmount) {
        this.realAmount.set(realAmount);
    }

    @Override
    public int compareTo(Bill bill) {
        return this.getDateOfOrder().compareTo(bill.getDateOfOrder());
    }
}
