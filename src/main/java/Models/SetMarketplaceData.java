package Models;

import Entities.SetEntity;

public class SetMarketplaceData {
    private SetEntity set;
    private boolean possede;
    private boolean courant;

    public SetMarketplaceData(SetEntity entity, boolean possede, boolean courant){
        this.set = entity;
        this.possede = possede;
        this.courant = courant;
    }

    public SetEntity getSet() {
        return set;
    }

    public boolean isCourant() {
        return courant;
    }

    public boolean isPossede() {
        return possede;
    }
}
