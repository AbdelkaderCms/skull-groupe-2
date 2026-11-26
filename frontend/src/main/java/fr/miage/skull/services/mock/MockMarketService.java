package fr.miage.skull.services.mock;

import fr.miage.skull.models.MarketSet;
import fr.miage.skull.services.MarketService;

import java.util.*;

public final class MockMarketService implements MarketService {
    private static final Map<Integer, Integer> PRICES = Map.of(1, 0, 2, 0, 3, 350, 4, 350);

    private static final class State {
        int coins = 1240;
        int equipped = 2;
        final Set<Integer> owned = new HashSet<>(Set.of(1, 2));
    }

    private final Map<String, State> states = new HashMap<>();

    private State state(String pseudo) {
        return states.computeIfAbsent(pseudo, key -> new State());
    }

    @Override
    public synchronized List<MarketSet> list(String pseudo) {
        State s = state(pseudo);
        return PRICES.keySet().stream().sorted()
                .map(id -> new MarketSet(id, PRICES.get(id), s.owned.contains(id), s.equipped == id))
                .toList();
    }

    @Override
    public boolean supportsChanges() {
        return true;
    }

    @Override
    public synchronized void buy(String pseudo, int id) {
        State s = state(pseudo);
        Integer price = PRICES.get(id);
        if (price == null) throw new IllegalArgumentException("Set inconnu");
        if (s.owned.contains(id)) throw new IllegalStateException("Set déjà possédé");
        if (s.coins < price) throw new IllegalStateException("Solde insuffisant");
        s.coins -= price;
        s.owned.add(id);
    }

    @Override
    public synchronized void equip(String pseudo, int id) {
        State s = state(pseudo);
        if (!s.owned.contains(id)) throw new IllegalStateException("Set non possédé");
        s.equipped = id;
    }

    @Override
    public synchronized int balance(String pseudo, int initial) {
        return state(pseudo).coins;
    }

    @Override
    public synchronized int equipped(String pseudo) {
        return state(pseudo).equipped;
    }
}
