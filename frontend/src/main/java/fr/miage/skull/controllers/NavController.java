package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NavController {
    @FXML
    private Label coins, points, name;

    @FXML
    private void initialize() {
        refreshProfile();
    }

    public void refreshProfile() {
        var u = AppConfig.user();
        coins.setText("◈ " + AppConfig.MARKET.balance(AppConfig.marketIdentity(), u.coins()));
        points.setText("◆ " + u.points());
        name.setText(u.name());
    }

    @FXML
    private void menu() {
        NavigationManager.go("menu");
    }

    @FXML
    private void ranking() {
        NavigationManager.go("ranking");
    }

    @FXML
    private void history() {
        NavigationManager.go("history");
    }

    @FXML
    private void marketplace() {
        NavigationManager.go("marketplace");
    }

    @FXML
    private void logout() {
        AppConfig.session(null);
        NavigationManager.go("login");
    }
}
