package fr.miage.skull.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private void createGame() {
        fr.miage.skull.utils.NavigationManager.go("create-game");
    }

    @FXML
    private Label feedback;

    @FXML
    private void pending() {
        feedback.setText("Cet écran sera ajouté à la prochaine étape.");
    }
}
