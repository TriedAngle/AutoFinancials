module autofinancials.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.json;
    requires java.sql;
    requires opencsv;
    requires postgresql;

    opens net.strobl.management;
    opens net.strobl.processing;
    opens net.strobl.frontend.general;
    opens net.strobl.frontend.tabs.dashboard;
    opens net.strobl.frontend.tabs.bills;
    opens net.strobl.frontend.tabs.techvintory;
    opens net.strobl.frontend.tabs.projects;
    opens net.strobl.frontend.tabs.settings;
}