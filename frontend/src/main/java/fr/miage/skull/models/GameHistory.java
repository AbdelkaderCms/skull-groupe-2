package fr.miage.skull.models;

import java.util.List;

public record GameHistory(String id, int challenges, List<String> players, String winner, boolean won, int points,
                          int coins, List<RoundResult> rounds) {
}
