package Mappers;

import DTOs.HistoriquePartieDTO;
import DTOs.ScoreDTO;
import Entities.JoueurEntity;
import Entities.MembreEntity;
import Entities.PartieEntity;
import org.springframework.stereotype.Component;

// Mapper du Ladder : convertit les entités en DTO
@Component
public class LadderMapper {

    // Convertit une partie en ligne d'historique
    public HistoriquePartieDTO toHistoriqueDto(PartieEntity partie) {
        if (partie == null) {
            return null;
        }
        JoueurEntity gagnant = partie.getGagnant();
        String pseudoGagnant = (gagnant == null) ? null : gagnant.getPseudo(); // pas de gagnant si la partie est en cours
        return new HistoriquePartieDTO(partie.getId(), partie.getDate(), partie.getNbJoueurs(), partie.getNbIA(), pseudoGagnant);
    }

    // Convertit un membre en ligne du tableau des scores
    public ScoreDTO toScoreDto(MembreEntity membre) {
        if (membre == null) {
            return null;
        }
        return new ScoreDTO(membre.getPseudo(), membre.getPoints());
    }
}