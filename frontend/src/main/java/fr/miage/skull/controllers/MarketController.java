package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.models.MarketSet;
import fr.miage.skull.utils.Background;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MarketController {
    @FXML
    private VBox rows;
    @FXML
    private Label status;
    @FXML
    private Button refresh;
    @FXML
    private NavController navController;

    @FXML
    private void initialize() {
        load();
    }

    @FXML
    private void load() {
        load("");
    }

    private void load(String message) {
        String identity = AppConfig.marketIdentity();
        rows.getChildren().clear();
        if (identity.isBlank()) {
            status.setText("Configurer SKULL_API_PSEUDO avant le test API.");
            return;
        }
        refresh.setDisable(true);
        status.setText("Chargement…");
        Background.run(() -> AppConfig.MARKET.list(identity), sets -> {
            for (MarketSet set : sets) rows.getChildren().add(createRow(set));
            if (AppConfig.user() != null && identity.equals(AppConfig.marketIdentity())) {
                navController.refreshProfile();
            }
            status.setText(sets.isEmpty() ? "Aucun set." : !message.isEmpty() ? message :
                    AppConfig.MARKET.supportsChanges() ? "Mode simulation — achats fictifs." :
                            "Mode API — achat et équipement indisponibles.");
            refresh.setDisable(false);
        }, error -> {
            status.setText("Marketplace inaccessible : " + error.getMessage());
            refresh.setDisable(false);
        });
    }

    private HBox createRow(MarketSet set) {
        HBox row = new HBox(18);
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().addAll("market-row", set.courant() ? "market-equipped" : "market-standard");
        Label name = new Label("Set " + set.id());
        name.setMinWidth(80);
        HBox tiles = TilePreview.create(set.id());
        HBox.setHgrow(tiles, Priority.ALWAYS);
        Label price = new Label(set.possede() ? "" : "◈ " + set.prix());
        price.setMinWidth(70);
        Button action = new Button(set.courant() ? "✓ Équipé" : set.possede() ? "✓ Équiper" : "Acheter");
        action.getStyleClass().add("market-action");
        action.setDisable(set.courant() || !AppConfig.MARKET.supportsChanges());
        action.setOnAction(event -> change(set));
        row.getChildren().addAll(name, tiles, price, action);
        return row;
    }

    private void change(MarketSet set) {
        String identity = AppConfig.marketIdentity();
        if (!set.possede()) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                    "Acheter le set " + set.id() + " pour " + set.prix() + " pièces ?",
                    ButtonType.OK, ButtonType.CANCEL);
            if (confirmation.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) return;
        }
        try {
            if (set.possede()) AppConfig.MARKET.equip(identity, set.id());
            else AppConfig.MARKET.buy(identity, set.id());
            load(set.possede() ? "Set équipé." : "Achat effectué. Tu peux maintenant équiper ce set.");
        } catch (RuntimeException error) {
            status.setText(error.getMessage());
        }
    }
}
