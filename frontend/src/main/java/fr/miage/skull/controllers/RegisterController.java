package fr.miage.skull.controllers;

import fr.miage.skull.config.AppConfig;
import fr.miage.skull.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label feedback;
    @FXML
    private PasswordField confirm;

    @FXML
    private void submit() {
        String address = email.getText().trim();
        String secret = password.getText();
        if (!address.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+") || secret.isBlank()) {
            feedback.setText("Adresse mail ou mot de passe invalide.");
            return;
        }
        if (!secret.equals(confirm.getText()) || secret.length() < 6) {
            feedback.setText("Vérifiez la confirmation et les 6 caractères minimum.");
            return;
        }
        var result = AppConfig.AUTH.register(address, secret);
        if (result.isEmpty()) {
            feedback.setText("Opération refusée.");
            return;
        }
        AppConfig.session(result.get());
        NavigationManager.go(result.get().admin() ? "admin" : "menu");
    }

    @FXML
    private void switchView() {
        NavigationManager.go("login");
    }
}