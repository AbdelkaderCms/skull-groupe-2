package fr.miage.skull.models;

public record User(String email, String name, int coins, int points, boolean admin) {
}