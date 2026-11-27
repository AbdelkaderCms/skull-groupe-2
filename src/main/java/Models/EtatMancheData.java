package Models;

import Entities.EnchereEntity;
import Entities.MancheEntity;

public class EtatMancheData {
    private MancheEntity manche;
    private EnchereEntity enchere;

    public EtatMancheData(MancheEntity manche, EnchereEntity enchere){
        this.manche = manche;
        this.enchere = enchere;
    }

    public MancheEntity getManche() {
        return manche;
    }

    public EnchereEntity getEnchere() {
        return enchere;
    }
}
