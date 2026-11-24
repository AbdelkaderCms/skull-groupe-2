package Mappers;

import DTOs.CreerPartieResponseDTO;
import Entities.JoueurEntity;
import Entities.PartieEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreerPartieResponseMapper {
    public CreerPartieResponseDTO toDto(PartieEntity entity){
        if(entity==null){
            return null;
        }
        Set<JoueurEntity> joueurListe= entity.getJoueurs();
        Set<String> nomsListe = new HashSet<>();        ;
        for (JoueurEntity joueur : joueurListe) {
            nomsListe.add(joueur.getPseudo());
        }
        return new CreerPartieResponseDTO(entity.getId(), nomsListe, entity.getNbIA(), entity.getNbJoueurs());
    }
}
