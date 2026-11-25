package Mappers;

import DTOs.CreerPartieResponseDTO;
import DTOs.SetMarketplaceDTO;
import Entities.JoueurEntity;
import Entities.PartieEntity;
import Entities.SetEntity;
import Models.SetMarketplaceData;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SetMarketPlaceMapper {
    public SetMarketplaceDTO toDto(SetMarketplaceData setMarketplaceData){
        if(setMarketplaceData==null){
            return null;
        }
        SetEntity set = setMarketplaceData.getSet();
        return new SetMarketplaceDTO(set.getId(), set.getPrix(), setMarketplaceData.isPossede(), setMarketplaceData.isCourant());
    }
}
