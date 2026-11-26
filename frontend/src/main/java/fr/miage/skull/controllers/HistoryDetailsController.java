package fr.miage.skull.controllers;

import fr.miage.skull.models.GameHistory;
import fr.miage.skull.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HistoryDetailsController {
    private static GameHistory selected;

    public static void select(GameHistory game) {
        selected = game;
    }

    @FXML
    private VBox rows;

    @FXML
    private void initialize() {
        if (selected == null) return;
        var title = new Label(selected.id());
        title.getStyleClass().addAll("heading", "dark");
        rows.getChildren().add(title);
        int n = 1;
        for (var round : selected.rounds()) {
            var row = new HBox(22, new Label("Manche " + n++), new Label("Challenger : " + round.challenger()), new Label("Enchère : " + round.bid()), new Label(round.success() ? "Réussite" : "Échec"), new Label("Tuile perdue : " + round.lostTile()));
            row.getStyleClass().add("data-row");
            rows.getChildren().add(row);
        }
    }

    @FXML
    private void back() {
        NavigationManager.go("history");
    }
}
