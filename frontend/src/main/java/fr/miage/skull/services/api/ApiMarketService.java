package fr.miage.skull.services.api;

import fr.miage.skull.models.MarketSet;
import fr.miage.skull.services.MarketService;

import java.util.List;

public final class ApiMarketService implements MarketService {
    private final ApiClient client;

    public ApiMarketService(ApiClient client) {
        this.client = client;
    }

    public List<MarketSet> list(String pseudo) throws Exception {
        return List.of(client.json.readValue(client.request("/api/market?pseudo=" + ApiClient.pseudo(pseudo), null, 200), MarketSet[].class));
    }
}
