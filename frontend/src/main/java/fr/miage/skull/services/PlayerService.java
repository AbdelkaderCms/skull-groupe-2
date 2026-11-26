package fr.miage.skull.services;

import fr.miage.skull.models.RankingEntry;
import fr.miage.skull.models.User;

import java.util.List;

public interface PlayerService {
    List<RankingEntry> ranking(User user);
}
