package fr.miage.skull.services;

import fr.miage.skull.models.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String email, String password);

    Optional<User> register(String email, String password);
}