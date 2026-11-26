package fr.miage.skull;

import fr.miage.skull.utils.NavigationManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public void start(Stage stage) {
        NavigationManager.init(stage);
        stage.setMinWidth(900);
        stage.setMinHeight(650);
        NavigationManager.go("login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}