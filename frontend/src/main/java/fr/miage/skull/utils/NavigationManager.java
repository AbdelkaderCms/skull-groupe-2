package fr.miage.skull.utils;

import fr.miage.skull.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class NavigationManager {

    private static Stage stage;

    public static void init(Stage s) {
        stage = s;
    }

    public static void go(String view) {
        try {
            Parent root = FXMLLoader.load(MainApp.class.getResource("/views/" + view + ".fxml"));
            if (stage.getScene() == null) {
                Scene scene = new Scene(root, 1100, 780);
                scene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());
                stage.setScene(scene);
            } else stage.getScene().setRoot(root);
            stage.setTitle("Skull - " + view);
        } catch (java.io.IOException e) {
            throw new IllegalStateException("Vue inaccessible : " + view, e);
        }
    }
}