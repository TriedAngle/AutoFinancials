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
    opens net.strobl.frontend.general to javafx.fxml;
    opens net.strobl.frontend.tabs.dashboard to javafx.fxml;
    opens net.strobl.frontend.tabs.bills to javafx.fxml;
    opens net.strobl.frontend.tabs.projects to javafx.fxml;
    opens net.strobl.frontend.tabs.settings to javafx.fxml;
}