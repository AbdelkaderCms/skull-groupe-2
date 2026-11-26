package Models;

import Entities.EnchereEntity;

public class EnchereCouranteData {
    private EnchereEntity enchere;
    private int enchereMinimale;

    public EnchereCouranteData(EnchereEntity enchere, int enchereMinimale){
        this.enchere = enchere;
        this.enchereMinimale = enchereMinimale;
    }

    public EnchereEntity getEnchere() {
        return enchere;
    }

    public int getEnchereMinimale() {
        return enchereMinimale;
    }
}
