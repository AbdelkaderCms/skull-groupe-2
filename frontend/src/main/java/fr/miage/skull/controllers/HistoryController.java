package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.models.GameHistory;
import fr.miage.skull.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HistoryController {
    @FXML
    private VBox rows;

    @FXML
    private void initialize() {
        for (var g : AppConfig.HISTORY.recent(AppConfig.user())) {
            var card = new VBox(10);
            card.getStyleClass().add("data-row");
            var header = new HBox(18, new Label(g.id()), new Label(g.won() ? "Victoire" : "Défaite"), new Label("Défis réussis : " + g.challenges()), new Label("Vainqueur : " + g.winner()));
            var players = new HBox(12, new Label("Joueurs :"));
            for (int i = 0; i < g.players().size(); i++) {
                String p = g.players().get(i);
                var item = new HBox(4, new Label(p));
                item.setAlignment(Pos.CENTER_LEFT);
                if (i != 0) {
                    var report = new Button("Signaler");
                    report.setVisible(false);
                    item.hoverProperty().addListener((o, old, v) -> report.setVisible(v));
                    report.setOnAction(e -> report(g, p));
                    item.getChildren().add(report);
                }
                players.getChildren().add(item);
            }
            var detail = new Button("Détail");
            detail.setOnAction(e -> {
                        HistoryDetailsController.select(g);
                        NavigationManager.go("history-details");
                    }
            );
            var footer = new HBox(20, new Label("Points : +" + g.points()), new Label("Pièces : +" + g.coins()), detail);
            card.getChildren().addAll(header, players, footer);
            rows.getChildren().add(card);
        }
    }

    private void report(GameHistory g, String player) {
        var dialog = new TextInputDialog();
        dialog.setTitle("Signalement");
        dialog.setHeaderText("Signaler " + player);
        dialog.setContentText("Motif :");
        dialog.showAndWait().filter(s -> !s.isBlank()).ifPresent(s -> {
            AppConfig.HISTORY.report(AppConfig.user(), g.id(), player, s.trim());
            new Alert(Alert.AlertType.INFORMATION, "Signalement enregistré dans le mock.").showAndWait();
        });
    }
}
