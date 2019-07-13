package net.strobl.data.postgresql;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.strobl.data.json.JSONManager;
import net.strobl.management.Manager;
import net.strobl.processing.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PostgreSQLDataManager {

    private Connection connection = null;

    // Credentials:
    // [0] = "username": "SampleName"
    // [1] = "password": "SamplePass",
    // [2] = "url": "jdbc:postgresql://SampleSite.com:5432/SampleDatabase"

    private String[] credentials = new String[3];

    private Boolean connected = false;
    public final String BILL_TABLE_NAME = "BILLS";
    public final String TECHNIC_INVENTORY = "TECHVINTORY";

    public void setCredentialsWithJSON() {
        credentials[0] = JSONManager.readCredentials()[0];
        credentials[1] = JSONManager.readCredentials()[1];
        credentials[2] = JSONManager.readCredentials()[2];
    }


    public void connectToDataBase() {
        try {
            Properties props = new Properties();
            props.setProperty("user", credentials[1]);
            props.setProperty("password", credentials[2]);
            props.setProperty("ssl","true");

            connection = DriverManager.getConnection(credentials[0], props);
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
        if (connection == null) {
            connected = false;
            System.out.println("Could not connect");
        } else {
            connected = true;
            System.out.println("Opened database successfully");
        }
    }

    public void testConnection(String url, String username, String password){
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("ssl","true");

            connection = DriverManager.getConnection(url, props);

        } catch (SQLException e) {
            connection = null;
            e.printStackTrace();
        }
        if (connection == null) {
            connected = false;
            System.out.println("Could not connect");
        } else {
            connected = true;
            System.out.println("Opened database successfully");
        }
    }

    public void closeCurrentDataBase() {
        try {
            connection.close();
            connected = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupDatabase(){
        if(!checkTableExistance(BILL_TABLE_NAME) && !checkTableExistance(TECHNIC_INVENTORY)) {
            createTableAtCurrentDatabase(BILL_TABLE_NAME);
            createTableAtCurrentDatabase(TECHNIC_INVENTORY);
            //TODO graphic response

        }else {
            //TODO graphic response
        }
    }

    public void createTableAtCurrentDatabase(String table) {
        Statement statement = null;
        try {
            if(table.equals(BILL_TABLE_NAME)){
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + table +
                        "(ID INT PRIMARY KEY                NOT NULL," +
                        " PROJECTNAME           TEXT        NOT NULL, " +
                        " AMOUNTINCENT          INT         NOT NULL, " +
                        " ISINTAKE              BOOLEAN     NOT NULL, " +
                        " ISDIGITAL             BOOLEAN     NOT NULL, " +
                        " ISPAID                BOOLEAN     NOT NULL, " +
                        " DATEORDER             TEXT                , " +
                        " DATERECEIVED          TEXT                , " +
                        " DATEPAYMENT           TEXT                , " +
                        " ORDEREDBY             TEXT                , " +
                        " SELLER                TEXT                , " +
                        " ITEMS                 TEXT[]              , " +
                        " REASON                TEXT                ) ";
                statement.executeUpdate(sql);
                statement.close();
            }else if(table.equals(TECHNIC_INVENTORY)){
                statement = connection.createStatement();

                String sql = "CREATE TABLE " + table +
                        "(ID INT PRIMARY KEY                NOT NULL, " +
                        " MODEL                 TEXT                , " +
                        " PODUCTID              TEXT                , " +
                        " PRODUCER              TEXT                , " +
                        " TYPE                  TEXT        NOT NULL, " +
                        " PRICEBOUGHT           INT                 , " +
                        " PRICEBOUGHTDATE       TEXT                , " +
                        " PRICECURR             INT                 , " +
                        " RPRICECURRDATE        TEXT                , " +
                        " DESCRIPTION           TEXT                ) ";

                statement.executeUpdate(sql);
                statement.close();
            }else {
                System.out.println("invalid tablename/table already exists");
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public ObservableList<String> getTableNames(){
        ObservableList<String> tableNames = FXCollections.observableArrayList();

        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = null;
            rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
            return tableNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableNames.add("0");
        return tableNames;
    }

    public boolean checkTableExistance(String tableName){
        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = null;
            rs = md.getTables(null, null, tableName, null);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkColumnExistance(String tableName, String columnName){
        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getColumns(null, null, tableName, columnName);
            if (rs.next()) {
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int getLatestID(String tableName){
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "SELECT MAX(ID) FROM" + tableName;
            ResultSet rs = statement.executeQuery(sql);
            int i = 0;
            if (rs.next()){
               i = rs.getInt(1)  + 1;
            }
            statement.close();
            connection.commit();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void resetSequence(){

    }

    public void insertData(int billID, String project, int amountInCent, boolean isIntake, boolean isDigital, boolean isPaid, String dateOfOrder, String dateOfReceive, String dateOfPayment, String orderedBy, String seller, String[] items, String reason) {
        Statement statement = null;
        try {
            Array itemArray = connection.createArrayOf("text", items);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql =
                    "INSERT INTO " + BILL_TABLE_NAME + " (ID,PROJECTNAME,AMOUNTINCENT,ISINTAKE,ISDIGITAL,ISPAID,DATEORDER,DATERECEIVED,DATEPAYMENT,ORDEREDBY,SELLER,ITEMS,REASON) "
                    + "VALUES (" + billID + "," + "'" + project + "'" + "," + amountInCent + "," + isIntake + "," + isDigital + "," + isPaid + "," + "'" + dateOfOrder + "'" + ","
                    + "'" + dateOfReceive + "'" + "," + "'" + dateOfPayment + "'" + "," + "'" + orderedBy + "'" + "," + "'" + seller + "'" + "," + "'" + itemArray + "'" + "," + "'" + reason + "'" + ");";

            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTechItem(int itemID, String model, String productID, String producer, String type, int priceBought,
                               String priceBoughtDate, int priceCurr, String priceCurrDate, String description){
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "INSERT INTO " + TECHNIC_INVENTORY + " (ID,MODEL,PRODUCTID,PRODUCER,TYPE,PRICEBOUGHT,PRICEBOUGHTDATE,PRICECURR,PRICECURRDATE,DESCRIPTION) "
                            + "VALUES (" + itemID + "," + "'" + model + "'" + "," + producer + "," + type + "," +
                    priceBought + "," + priceBoughtDate + "," + "'" + priceCurr + "'" + "," + "'" + priceCurrDate + "'" + "," + "'" +
                    description + "'" + ");";

            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(int index) {
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "DELETE FROM smvbills WHERE id = " + index + ";";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBill(int billID, String project, int amountInCent, boolean isIntake, boolean isDigital, boolean isPaid, String dateOfOrder, String dateOfReceive, String dateOfPayment, String orderedBy, String seller, String[] items, String reason){
        Statement statement = null;
        Array itemArray = null;
        try {
            itemArray = connection.createArrayOf("text", items);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            //String sql = "UPDATE " + " SMVBILLS " + " (PROJECTNAME,AMOUNTINCENT,ISINTAKE,ISDIGITAL,ISPAID,DATEORDER,DATERECEIVED,DATEPAYMENT,ORDEREDBY,SELLER,ITEMS,REASON) "
            //        + "VALUES (" + "'" + project + "'" + "," + amountInCent + "," + isIntake + "," + isDigital + "," + isPaid + "," + "'" + dateOfOrder + "'" + ","
            //        + "'" + dateOfReceive + "'" + "," + "'" + dateOfPayment + "'" + "," + "'" + orderedBy + "'" + "," + "'" + seller + "'" + "," + "'" + itemArray + "'" + "," + "'" + reason + "'" + " WHERE id = " + billID + ");";

            String  sql = "UPDATE smvbills " +
                    "SET " +
                    "projectname = " + "'" + project + "'" + ", " +
                    "amountincent = " + amountInCent + ", " +
                    "isintake = " + isIntake + ", " +
                    "isdigital = " + isDigital + ", " +
                    "ispaid = " + isPaid + ", " +
                    "dateorder = " + "'" + dateOfOrder + "'" + ", " +
                    "datereceived = " + "'" + dateOfReceive  + "'" + ", " +
                    "datepayment = " + "'" + dateOfPayment + "'" + ", " +
                    "orderedby = " + "'" + orderedBy + "'" + ", " +
                    "seller = " + "'" + seller + "'" + ", " +
                    "items = " + "'" + itemArray + "'" + ", " +
                    "reason = " + "'" + reason + "' "  +
                    "WHERE id = " + billID;
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTechItem(){

    }

    public Bill getSingleBill(int index){
        Statement statement = null;
        Bill bill = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "SELECT * FROM smvbills WHERE id = " + index + ";";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                SimpleIntegerProperty billID = new SimpleIntegerProperty(rs.getInt("id"));
                SimpleStringProperty project = new SimpleStringProperty(rs.getString("projectname"));
                SimpleIntegerProperty amountInCent = new SimpleIntegerProperty(rs.getInt("amountincent"));
                SimpleBooleanProperty isIntake = new SimpleBooleanProperty(rs.getBoolean("isintake"));
                SimpleBooleanProperty isDigital = new SimpleBooleanProperty(rs.getBoolean("isdigital"));
                SimpleBooleanProperty isPaid = new SimpleBooleanProperty(rs.getBoolean("ispaid"));
                SimpleStringProperty dateOfOrder = new SimpleStringProperty(rs.getString("dateorder"));
                SimpleStringProperty dateOfReceive = new SimpleStringProperty(rs.getString("datereceived"));
                SimpleStringProperty dateOfPayment = new SimpleStringProperty(rs.getString("datepayment"));
                SimpleStringProperty orderedBy = new SimpleStringProperty(rs.getString("orderedby"));
                SimpleStringProperty seller = new SimpleStringProperty(rs.getString("seller"));
                Array arrayItems = rs.getArray("items");
                String[] items = (String[]) arrayItems.getArray();
                ObservableList<String> observableItems = FXCollections.observableArrayList(items);
                SimpleStringProperty reason = new SimpleStringProperty(rs.getString("reason"));
                bill = new Bill(billID, project, amountInCent, isIntake, isDigital, isPaid, dateOfOrder, dateOfReceive, dateOfPayment, reason, orderedBy, seller, observableItems);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            SimpleIntegerProperty billID = new SimpleIntegerProperty(0);
            SimpleStringProperty project = new SimpleStringProperty("");
            SimpleIntegerProperty amountInCent = new SimpleIntegerProperty(0);
            SimpleBooleanProperty isIntake = new SimpleBooleanProperty(false);
            SimpleBooleanProperty isDigital = new SimpleBooleanProperty(false);
            SimpleBooleanProperty isPaid = new SimpleBooleanProperty(false);
            SimpleStringProperty dateOfOrder = new SimpleStringProperty("");
            SimpleStringProperty dateOfReceive = new SimpleStringProperty("");
            SimpleStringProperty dateOfPayment = new SimpleStringProperty("");
            SimpleStringProperty orderedBy = new SimpleStringProperty("");
            SimpleStringProperty seller = new SimpleStringProperty("");
            String[] items = new String[0];
            ObservableList<String> observableItems = FXCollections.observableArrayList(items);
            SimpleStringProperty reason = new SimpleStringProperty("");
            bill = new Bill(billID, project, amountInCent, isIntake, isDigital, isPaid, dateOfOrder, dateOfReceive, dateOfPayment, reason, orderedBy, seller, observableItems);
        }
        return bill;
    }

    public ObservableList<Bill> fetchFilteredBills(String filterName, Boolean showUnpaid) {
        ObservableList<Bill> bills = FXCollections.observableArrayList();
        Statement statement = null;
        if (connected) {
            try {
                connection.setAutoCommit(false);

                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(fetchFilter(filterName, showUnpaid));
                while (rs.next()) {
                    SimpleIntegerProperty billID = new SimpleIntegerProperty(rs.getInt("id"));
                    SimpleStringProperty project = new SimpleStringProperty(rs.getString("projectname"));
                    SimpleIntegerProperty amountInCent = new SimpleIntegerProperty(rs.getInt("amountincent"));
                    SimpleBooleanProperty isIntake = new SimpleBooleanProperty(rs.getBoolean("isintake"));
                    SimpleBooleanProperty isDigital = new SimpleBooleanProperty(rs.getBoolean("isdigital"));
                    SimpleBooleanProperty isPaid = new SimpleBooleanProperty(rs.getBoolean("ispaid"));
                    SimpleStringProperty dateOfOrder = new SimpleStringProperty(rs.getString("dateorder"));
                    SimpleStringProperty dateOfReceive = new SimpleStringProperty(rs.getString("datereceived"));
                    SimpleStringProperty dateOfPayment = new SimpleStringProperty(rs.getString("datepayment"));
                    SimpleStringProperty orderedBy = new SimpleStringProperty(rs.getString("orderedby"));
                    SimpleStringProperty seller = new SimpleStringProperty(rs.getString("seller"));
                    Array arrayItems = rs.getArray("items");
                    String[] items = (String[]) arrayItems.getArray();
                    ObservableList<String> observableItems = FXCollections.observableArrayList(items);
                    SimpleStringProperty reason = new SimpleStringProperty(rs.getString("reason"));
                    /*
                    if(!isIntake.get()){
                        amountInCent.set(-amountInCent.get());
                    }
                    */

                    bills.add(new Bill(billID, project, amountInCent, isIntake, isDigital, isPaid, dateOfOrder, dateOfReceive, dateOfPayment, reason, orderedBy, seller, observableItems));
                }
                rs.close();
                statement.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        } else {
            SimpleIntegerProperty billID = new SimpleIntegerProperty(0);
            SimpleStringProperty project = new SimpleStringProperty("");
            SimpleIntegerProperty amountInCent = new SimpleIntegerProperty(0);
            SimpleBooleanProperty isIntake = new SimpleBooleanProperty(false);
            SimpleBooleanProperty isDigital = new SimpleBooleanProperty(false);
            SimpleBooleanProperty isPaid = new SimpleBooleanProperty(false);
            SimpleStringProperty dateOfOrder = new SimpleStringProperty("");
            SimpleStringProperty dateOfReceive = new SimpleStringProperty("");
            SimpleStringProperty dateOfPayment = new SimpleStringProperty("");
            SimpleStringProperty orderedBy = new SimpleStringProperty("");
            SimpleStringProperty seller = new SimpleStringProperty("");
            SimpleStringProperty reason = new SimpleStringProperty("");
            ObservableList<String> observableItems = FXCollections.observableArrayList();
            observableItems.add("");
            bills.add(new Bill(billID, project, amountInCent, isIntake, isDigital, isPaid, dateOfOrder, dateOfReceive, dateOfPayment, reason, orderedBy, seller, observableItems));

        }

        return bills;
    }

    private String fetchFilter(String filterName, Boolean showUnpaid) {
        String sql = "";
        if (!filterName.equals("All")) {
            for (String name : Manager.getDataManager().getPostgreSQLData().getProjectNames()) {
                if (name.equals(filterName)) {
                    if (!showUnpaid) {
                        sql = "SELECT * FROM smvbills WHERE projectname LIKE '" + name + "'" + "AND ispaid = true";
                    } else {
                        sql = "SELECT * FROM smvbills WHERE projectname LIKE '" + name + "'";
                    }
                }
            }
        }
        if (filterName.equals("All")) {
            if (!showUnpaid) {
                sql = "SELECT * FROM smvbills WHERE ispaid = true";
            } else {
                sql = "SELECT * FROM smvbills";
            }
        }

        return sql;
    }


    public List<String> getProjectNames() {
        List<String> projectnames = new ArrayList<>();
        Statement statement = null;

        if (connected.equals(true)) {
            try {
                statement = connection.createStatement();
                String sql = "SELECT DISTINCT projectname FROM smvbills";
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    projectnames.add(rs.getString("projectname"));
                }
                rs.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else {
            projectnames.add("no data");
        }
        return projectnames;
    }

    @Deprecated
    public void migrateDataFromCSVToDataBase() {
        //This code will migrate copy and paste all data from a csv File into an already connected Database;

        Statement statement = null;
        try {

            //Create the DataTable
            statement = connection.createStatement();
            String sql = "CREATE TABLE SMVBILLS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " PROJECTNAME           TEXT        NOT NULL, " +
                    " AMOUNTINCENT          INT         NOT NULL, " +
                    " ISINTAKE              BOOLEAN     NOT NULL, " +
                    " ISDIGITAL             BOOLEAN     NOT NULL, " +
                    " ISPAID                BOOLEAN     NOT NULL, " +
                    " DATEORDER             TEXT                , " +
                    " DATERECEIVED          TEXT                , " +
                    " DATEPAYMENT           TEXT                , " +
                    " ORDEREDBY             TEXT                , " +
                    " SELLER                TEXT                , " +
                    " ITEMS                 TEXT[]              , " +
                    " REASON                TEXT                  )";
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        //Unnecessary line because items were never recorded
        String[] allItems = new String[Manager.getDataManager().getCSVData().getAllCSVBills().size() + 1];

        //Get each bill and push it into the database
        for (Bill bill : Manager.getDataManager().getCSVData().getAllCSVBills()) {
            //Database starts counting at index 1 so for better overview the id should start at 1 too, hence the +1
            int billID = Manager.getDataManager().getCSVData().getAllCSVBills().indexOf(bill) + 1;
            String project = bill.getProject();
            int amountInCent = bill.getAmountInCent();
            boolean isIntake = bill.isIsIntake();
            boolean isDigital = bill.isIsDigital();
            boolean isPaid = bill.isIsPaid();
            String dateOfOrder = bill.getDateOfOrder();
            String dateOfReceive = bill.getDateOfReceive();
            String dateOfPayment = bill.getDateOfPayment();
            String orderedBy = bill.getOrderedBy();
            String seller = bill.getSeller();

            //Unnecessary lines because items were never recorded
            ObservableList<String> items = bill.getItems();
            for (int i = 0; i < items.size(); i++) {
                allItems[i] = items.get(i);
            }


            String reason = bill.getReason();
            statement = null;
            try {
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                String sql = "INSERT INTO " + " SMVBILLS " + " (ID,PROJECTNAME,AMOUNTINCENT,ISINTAKE,ISDIGITAL,ISPAID,DATEORDER,DATERECEIVED,DATEPAYMENT,ORDEREDBY,SELLER,ITEMS,REASON) "
                        + "VALUES (" + billID + "," + "'" + project + "'" + "," + amountInCent + "," + isIntake + "," + isDigital + "," + isPaid + "," + "'" + dateOfOrder + "'" + ","
                        + "'" + dateOfReceive + "'" + "," + "'" + dateOfPayment + "'" + "," + "'" + orderedBy + "'" + "," + "'" + seller + "'" + "," + "'" + " {0} " + "'" + "," + "'" + reason + "'" + ");";

                statement.executeUpdate(sql);
                statement.close();
                connection.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public Boolean isConnected() {
        return connected;
    }

}