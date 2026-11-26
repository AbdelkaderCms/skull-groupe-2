package fr.miage.skull.services.mock;

import fr.miage.skull.models.MarketSet;
import fr.miage.skull.services.MarketService;

import java.util.List;

public final class MockMarketService implements MarketService {
    public List<MarketSet> list(String pseudo) {
        return List.of(new MarketSet(1, 0, true, true), new MarketSet(2, 300, false, false), new MarketSet(3, 600, false, false));
    }
}
