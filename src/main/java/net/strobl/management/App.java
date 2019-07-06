package net.strobl.management;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    private double x, y;
    private Manager manager;
    private DataManager dataManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        manager = new Manager(this);
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/net/strobl/frontend/general/MainLayout.fxml"));
        Scene scene = new Scene(root, Color.TRANSPARENT);
        scene.getStylesheets().addAll(getClass().getResource("/net/strobl/frontend/general/MainStyleSheet.css").toExternalForm());
        stage.setScene(scene);


        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseReleased(event ->{
            stage.setOpacity(1);
        });
        root.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.75);
        });
        stage.show();
    }

    public static void exit(){
        if(Manager.getDataManager().getPostgreSQLDataManager().isConnected()){
            Manager.getDataManager().getPostgreSQLDataManager().closeCurrentDataBase();
        }
        Platform.exit();
    }
}
