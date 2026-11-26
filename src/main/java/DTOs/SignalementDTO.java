package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

// Une ligne de GET /signalements (contrat API : membre, message)
public class SignalementDTO {
    @Schema(description = "Pseudo du membre signalé", example = "jean123", accessMode = Schema.AccessMode.READ_ONLY)
    private String membre;
    @Schema(description = "Motif du signalement", example = "Suspicion de triche", accessMode = Schema.AccessMode.READ_ONLY)
    private String message;

    public SignalementDTO(String membre, String message) {
        this.membre = membre;
        this.message = message;
    }

    public String getMembre() {
        return membre;
    }

    public String getMessage() {
        return message;
    }
}