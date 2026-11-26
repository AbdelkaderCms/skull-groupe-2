package fr.miage.skull.services.mock;

import fr.miage.skull.models.RankingEntry;
import fr.miage.skull.models.User;
import fr.miage.skull.services.PlayerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MockPlayerService implements PlayerService {
    public List<RankingEntry> ranking(User user) {
        var rows = new ArrayList<>(List.of(new RankingEntry("Luna", 480, 24, false), new RankingEntry("Victor", 320, 16, false), new RankingEntry("Nora", 180, 9, false), new RankingEntry("Hugo", 90, 4, false), new RankingEntry(user.name(), user.points(), 0, true)));
        rows.sort(Comparator.comparingInt(RankingEntry::points).reversed());
        return List.copyOf(rows);
    }
}
