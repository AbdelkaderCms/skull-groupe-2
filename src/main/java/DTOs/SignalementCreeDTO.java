package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

// Réponse 201 de POST /signalements : l'identifiant du signalement créé
public class SignalementCreeDTO {
    @Schema(description = "Identifiant du signalement créé", example = "5", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer signalementId;

    public SignalementCreeDTO(Integer signalementId) {
        this.signalementId = signalementId;
    }

    public Integer getSignalementId() {
        return signalementId;
    }
}