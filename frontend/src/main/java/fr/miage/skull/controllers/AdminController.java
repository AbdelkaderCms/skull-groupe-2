package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.utils.NavigationManager;
import javafx.fxml.FXML;

public class AdminController {
    @FXML
    private void logout() {
        AppConfig.session(null);
        NavigationManager.go("login");
    }
}