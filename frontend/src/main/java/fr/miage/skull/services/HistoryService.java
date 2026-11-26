package fr.miage.skull.services;

import fr.miage.skull.models.GameHistory;
import fr.miage.skull.models.User;

import java.util.List;

public interface HistoryService {
    List<GameHistory> recent(User user);

    void report(User reporter, String gameId, String player, String reason);
}
