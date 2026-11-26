package fr.miage.skull.services;

import fr.miage.skull.models.MarketSet;

import java.util.List;

public interface MarketService {
    List<MarketSet> list(String pseudo) throws Exception;

    default boolean supportsChanges() {
        return false;
    }

    default void buy(String pseudo, int id) {
        throw new UnsupportedOperationException("Achat indisponible");
    }

    default void equip(String pseudo, int id) {
        throw new UnsupportedOperationException("Équipement indisponible");
    }

    default int balance(String pseudo, int initial) {
        return initial;
    }

    default int equipped(String pseudo) {
        return 1;
    }
}
