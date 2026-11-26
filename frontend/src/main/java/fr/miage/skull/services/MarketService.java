package fr.miage.skull.services;

import fr.miage.skull.models.MarketSet;

import java.util.List;

public interface MarketService {
    List<MarketSet> list(String pseudo) throws Exception;
}
