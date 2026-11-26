package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MenuController {
    @FXML
    private Label feedback;
    @FXML
    private HBox equippedTiles;

    @FXML
    private void initialize() {
        equippedTiles.getChildren().setAll(TilePreview.create(
                AppConfig.MARKET.equipped(AppConfig.marketIdentity())));
        if (AppConfig.API_MODE) feedback.setText("Aperçu provisoire du set en mode API.");
    }

    @FXML
    private void createGame() {
        NavigationManager.go("create-game");
    }

    @FXML
    private void pending() {
        feedback.setText("Cet écran sera ajouté à la prochaine étape.");
    }
}
