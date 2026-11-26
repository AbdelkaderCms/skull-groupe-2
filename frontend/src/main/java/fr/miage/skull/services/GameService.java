package fr.miage.skull.services;

import fr.miage.skull.models.CreatedGame;

public interface GameService {
    CreatedGame create(String pseudo, int nbJoueurs, int nbIA) throws Exception;
}
