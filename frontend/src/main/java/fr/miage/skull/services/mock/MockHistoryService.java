package fr.miage.skull.services.mock;

import fr.miage.skull.models.GameHistory;
import fr.miage.skull.models.RoundResult;
import fr.miage.skull.models.User;
import fr.miage.skull.services.HistoryService;

import java.util.ArrayList;
import java.util.List;

public class MockHistoryService implements HistoryService {
    private final List<String> reports = new ArrayList<>();

    public List<GameHistory> recent(User u) {
        var result = new ArrayList<GameHistory>();
        for (int i = 5; i >= 1; i--) {
            boolean won = i % 2 == 1;
            String winner = won ? u.name() : "Luna";
            var rounds = List.of(new RoundResult(u.name(), 3, true, "—"), new RoundResult("Victor", 4, false, "Fleur"), new RoundResult(winner, 2, true, "—"));
            result.add(new GameHistory("Partie" + i, won ? 2 : 1, List.of(u.name(), "Luna", "Victor"), winner, won, won ? 30 : 5, won ? 100 : 20, rounds));
        }
        return List.copyOf(result);
    }

    public void report(User reporter, String gameId, String player, String reason) {
        if (reason.isBlank()) throw new IllegalArgumentException("Motif obligatoire");
        reports.add(reporter.email() + " | " + gameId + " | " + player + " | " + reason);
    }
}
