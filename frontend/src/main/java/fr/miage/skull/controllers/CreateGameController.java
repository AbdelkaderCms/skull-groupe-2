package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.utils.Background;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CreateGameController {
    @FXML
    private ComboBox<Integer> players, bots;
    @FXML
    private Label status;
    @FXML
    private Button create;

    @FXML
    private void initialize() {
        players.getItems().addAll(3, 4, 5, 6);
        players.valueProperty().addListener((o, a, b) -> update());
        players.setValue(4);
    }

    private void update() {
        bots.getItems().clear();
        for (int i = 0; i < players.getValue(); i++)
            bots.getItems().add(i);
        bots.setValue(0);
    }

    @FXML
    private void create() {
        String pseudo = AppConfig.pseudo();
        if (pseudo.isBlank()) {
            status.setText("Configurer SKULL_API_PSEUDO avant le test API.");
            return;
        }
        int n = players.getValue(), ia = bots.getValue();
        create.setDisable(true);
        players.setDisable(true);
        bots.setDisable(true);
        status.setText("Création…");
        Background.run(() -> AppConfig.GAME.create(pseudo, n, ia), g -> {
                    status.setText("Partie n°" + g.id() + " — " + g.pseudoJoueurs().size() + " / " + g.nbJoueurs() + " joueurs : " + String.join(", ", g.pseudoJoueurs()) + ". Salle d’attente pas ecore faite.");
                },
                error -> {
                    status.setText("Création non confirmée : " + error.getMessage() + ". Vérifier avant de réessayer.");
                });
    }
}
