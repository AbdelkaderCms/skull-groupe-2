package fr.miage.skull.services.mock;

import fr.miage.skull.models.CreatedGame;
import fr.miage.skull.services.GameService;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public final class MockGameService implements GameService {
    private final AtomicLong ids = new AtomicLong();

    public CreatedGame create(String pseudo, int n, int ia) {
        if (n < 3 || n > 6 || ia < 0 || ia >= n) throw new IllegalArgumentException("Paramètres invalides");
        Set<String> names = new LinkedHashSet<>();
        names.add(pseudo);
        for (int i = 0; i < ia; i++) names.add("IA " + (i + 1));
        return new CreatedGame(ids.incrementAndGet(), Set.copyOf(names), n, ia);
    }
}
