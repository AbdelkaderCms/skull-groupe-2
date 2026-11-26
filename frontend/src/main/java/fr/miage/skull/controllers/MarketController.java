package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.utils.Background;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MarketController {
    @FXML
    private VBox rows;
    @FXML
    private Label status;
    @FXML
    private Button refresh;

    @FXML
    private void initialize() {
        load();
    }

    @FXML
    private void load() {
        String pseudo = AppConfig.pseudo();
        rows.getChildren().clear();
        if (pseudo.isBlank()) {
            status.setText("Configurer SKULL_API_PSEUDO avant le test API.");
            return;
        }
        refresh.setDisable(true);
        status.setText("Chargement…");
        Background.run(() -> AppConfig.MARKET.list(pseudo), sets -> {
            for (var set : sets) {
                var row = new HBox(20);
                row.getStyleClass().add("data-row");
                row.getChildren().addAll(new Label("Set n°" + set.id()), new Label(set.prix() + " pièces"), new Label(set.courant() ? "Équipé" : set.possede() ? "Possédé" : "Disponible"));
                rows.getChildren().add(row);
            }
            status.setText(sets.isEmpty() ? "Aucun set." : "Achat et équipement indisponibles pour le moment.");
            refresh.setDisable(false);
        }, error -> {
            status.setText("Marketplace inaccessible : " + error.getMessage());
            refresh.setDisable(false);
        });
    }
}
