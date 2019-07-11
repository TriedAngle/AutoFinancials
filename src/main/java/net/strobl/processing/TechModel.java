package net.strobl.processing;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TechModel {
    private SimpleIntegerProperty modelID;
    private SimpleStringProperty model;
    private SimpleIntegerProperty productID;
    private SimpleStringProperty producer;
    private SimpleStringProperty type;
    private SimpleIntegerProperty priceBought;
    private SimpleStringProperty priceBoughtDate;
    private SimpleIntegerProperty priceCurr;
    private SimpleStringProperty priceCurrDate;
    private SimpleStringProperty description;

    public TechModel(int modelID, String model, int productID, String producer, String type, int priceBought,
    String priceBoughtDate, int priceCurr, String priceCurrDate, String description){
        this.modelID = new SimpleIntegerProperty(modelID);
        this.model = new SimpleStringProperty(model);
        this.productID = new SimpleIntegerProperty(productID);
        this.producer = new SimpleStringProperty(producer);
        this.type = new SimpleStringProperty(type);
        this.priceBought = new SimpleIntegerProperty(priceBought);
        this.priceBoughtDate = new SimpleStringProperty(priceBoughtDate);
        this.priceCurr = new SimpleIntegerProperty(priceCurr);
        this.priceCurrDate = new SimpleStringProperty(priceCurrDate);
        this.description = new SimpleStringProperty(description);
    }

    public int getModelID() {
        return modelID.get();
    }

    public SimpleIntegerProperty modelIDProperty() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID.set(modelID);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getProductID() {
        return productID.get();
    }

    public SimpleIntegerProperty productIDProperty() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public String getProducer() {
        return producer.get();
    }

    public SimpleStringProperty producerProperty() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer.set(producer);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getPriceBought() {
        return priceBought.get();
    }

    public SimpleIntegerProperty priceBoughtProperty() {
        return priceBought;
    }

    public void setPriceBought(int priceBought) {
        this.priceBought.set(priceBought);
    }

    public String getPriceBoughtDate() {
        return priceBoughtDate.get();
    }

    public SimpleStringProperty priceBoughtDateProperty() {
        return priceBoughtDate;
    }

    public void setPriceBoughtDate(String priceBoughtDate) {
        this.priceBoughtDate.set(priceBoughtDate);
    }

    public int getPriceCurr() {
        return priceCurr.get();
    }

    public SimpleIntegerProperty priceCurrProperty() {
        return priceCurr;
    }

    public void setPriceCurr(int priceCurr) {
        this.priceCurr.set(priceCurr);
    }

    public String getPriceCurrDate() {
        return priceCurrDate.get();
    }

    public SimpleStringProperty priceCurrDateProperty() {
        return priceCurrDate;
    }

    public void setPriceCurrDate(String priceCurrDate) {
        this.priceCurrDate.set(priceCurrDate);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
