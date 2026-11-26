package fr.miage.skull.models;

import java.util.Set;

public record CreatedGame(long id, Set<String> pseudoJoueurs, int nbJoueurs, int nbIA) {
}
