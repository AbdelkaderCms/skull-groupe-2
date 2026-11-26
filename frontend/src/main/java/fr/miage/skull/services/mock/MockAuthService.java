package fr.miage.skull.services.mock;

import fr.miage.skull.models.User;
import fr.miage.skull.services.AuthService;

import java.util.Optional;

// Simulation uniquement : aucun mot de passe stocké ni vérifié.
public class MockAuthService implements AuthService {
    public Optional<User> login(String email, String password) {
        if (!email.contains("@") || password.isBlank()) return Optional.empty();
        return Optional.of(new User(email, email.split("@", 2)[0], 1240, 220, email.equalsIgnoreCase("admin@skull.test")));
    }

    public Optional<User> register(String email, String password) {
        if (!email.contains("@") || password.length() < 6 || email.equalsIgnoreCase("admin@skull.test"))
            return Optional.empty();
        return Optional.of(new User(email, email.split("@", 2)[0], 1240, 0, false));
    }
}