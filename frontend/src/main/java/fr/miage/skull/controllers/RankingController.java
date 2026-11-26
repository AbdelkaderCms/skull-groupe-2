package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RankingController {
    @FXML
    private VBox rows;

    @FXML
    private void initialize() {
        addRow("Rang", "Joueur", "Points", "Victoires", "data-header");
        int rank = 1;
        for (var e : AppConfig.PLAYERS.ranking(AppConfig.user()))
            addRow("#" + rank++, e.name(), "" + e.points(), "" + e.wins(), e.currentUser() ? "data-selected" : "data-row");
    }

    private void addRow(String a, String b, String c, String d, String style) {
        var row = new HBox(16);
        row.getStyleClass().add(style);
        for (String value : new String[]{a, b, c, d}) {
            var l = new Label(value);
            l.setMaxWidth(Double.MAX_VALUE);
            l.setPrefWidth(200);
            HBox.setHgrow(l, Priority.ALWAYS);
            row.getChildren().add(l);
        }
        rows.getChildren().add(row);
    }
}
