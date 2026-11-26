package fr.miage.skull.services.api;

import fr.miage.skull.models.CreatedGame;
import fr.miage.skull.services.GameService;

import java.util.Map;

public final class ApiGameService implements GameService {
    private final ApiClient client;

    public ApiGameService(ApiClient client) {
        this.client = client;
    }

    public CreatedGame create(String pseudo, int nbJoueurs, int nbIA) throws Exception {
        if (nbJoueurs < 3 || nbJoueurs > 6 || nbIA < 0 || nbIA >= nbJoueurs)
            throw new IllegalArgumentException("Nombre de joueurs ou IA invalide");
        var body = client.json.writeValueAsString(Map.of("nbJoueurs", nbJoueurs, "nbIA", nbIA));
        var result = client.json.readValue(client.request("/api/game?pseudo=" + ApiClient.pseudo(pseudo), body, 201), CreatedGame.class);
        if (result.id() <= 0 || result.nbJoueurs() != nbJoueurs || result.nbIA() != nbIA || result.pseudoJoueurs() == null || !result.pseudoJoueurs().contains(pseudo))
            throw new IllegalStateException("Partie créée côté serveur, réponse incohérente : vérifier avant de réessayer.");
        return result;
    }
}
