package Mappers;

import DTOs.EnchereCouranteDTO;
import DTOs.EtatMancheDTO;
import Entities.EnchereEntity;
import Entities.JoueurEntity;
import Entities.MancheEntity;
import Models.EnchereCouranteData;
import Models.EtatMancheData;
import org.springframework.stereotype.Component;

@Component
public class EnchereMapper {
    public EnchereCouranteDTO toDto(EnchereCouranteData data){
        if(data==null){
            return null;
        }
        EnchereEntity enchere = data.getEnchere();
        if(enchere==null){
            return new EnchereCouranteDTO(null, null, data.getEnchereMinimale());
        }
        return new EnchereCouranteDTO(enchere.getValeur(), pseudo(enchere.getJoueurEntity()), data.getEnchereMinimale());
    }

    public EtatMancheDTO toDto(EtatMancheData data){
        if(data==null){
            return null;
        }
        MancheEntity manche = data.getManche();
        EnchereEntity enchere = data.getEnchere();
        return new EtatMancheDTO(
                manche.getId(),
                manche.getPhase().name(),
                enchere.getValeur(),
                pseudo(enchere.getJoueurEntity()),
                pseudo(manche.getJoueurCourant()),
                pseudo(manche.getJoueurEntity()));
    }

    private String pseudo(JoueurEntity joueur){
        return joueur==null ? null : joueur.getPseudo();
    }
}
