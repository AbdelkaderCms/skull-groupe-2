package Mappers;

import DTOs.EnchereCouranteDTO;
import DTOs.EtatMancheDTO;
import Models.EnchereCouranteData;
import Models.EtatMancheData;
import org.springframework.stereotype.Component;

@Component
public class EnchereMapper {
    public EnchereCouranteDTO toDto(EnchereCouranteData data){
        if(data==null){
            return null;
        }
        return new EnchereCouranteDTO(data.getValeur(), data.getPseudoJoueur(), data.getEnchereMinimale());
    }

    public EtatMancheDTO toDto(EtatMancheData data){
        if(data==null){
            return null;
        }
        return new EtatMancheDTO(data.getMancheId(), data.getValeur(), data.getPseudoEncherisseur(),
                data.getPseudoChallenger() != null, data.getPseudoChallenger());
    }
}
