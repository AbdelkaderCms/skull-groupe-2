package Mappers;

import DTOs.CreerEnchereDTO;
import DTOs.EnchereCouranteDTO;
import DTOs.EtatMancheDTO;
import Entities.EnchereEntity;
import Entities.JoueurEntity;
import Entities.MancheEntity;
import Models.EtatMancheData;
import org.springframework.stereotype.Component;

@Component
public class EnchereMapper {
    public EnchereEntity toEntity(CreerEnchereDTO dto){
        return new EnchereEntity(dto.getValeur());
    }

    public EnchereCouranteDTO toDto(EnchereEntity enchere){
        if(enchere==null){
            return new EnchereCouranteDTO(0, null);
        }
        return new EnchereCouranteDTO(enchere.getValeur(), pseudo(enchere.getMembreEntity()));
    }

    public EtatMancheDTO toDto(EtatMancheData data){
        if(data==null){
            return null;
        }
        MancheEntity manche = data.getManche();
        EnchereEntity enchere = data.getEnchere();
        return new EtatMancheDTO(manche.getId(), enchere.getValeur(), pseudo(enchere.getMembreEntity()),
                pseudo(manche.getJoueurCourant()), pseudo(manche.getJoueurEntity()));
    }

    private String pseudo(JoueurEntity joueur){
        if(joueur==null){
            return null;
        }
        return joueur.getPseudo();
    }
}
