package Mappers;

import DTOs.SignalementCreeDTO;
import DTOs.SignalementDTO;
import Entities.MembreEntity;
import Entities.SignalementEntity;
import org.springframework.stereotype.Component;

// Mapper des signalements : convertit les entités en DTO
@Component
public class SignalementMapper {

    // Convertit un signalement en ligne de la liste (pseudo du membre signalé + motif)
    public SignalementDTO toDto(SignalementEntity entity) {
        if (entity == null) {
            return null;
        }
        MembreEntity membre = entity.getMembreEntity();
        String pseudo = (membre == null) ? null : membre.getPseudo();
        return new SignalementDTO(pseudo, entity.getMotif());
    }

    // Convertit un signalement en réponse de création (son identifiant)
    public SignalementCreeDTO toCreeDto(SignalementEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SignalementCreeDTO(entity.getId());
    }
}