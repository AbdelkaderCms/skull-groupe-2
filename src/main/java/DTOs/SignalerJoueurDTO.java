package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

// Corps de la requête POST /signalements (contrat API : idJoueurSignale, motif, description)
public class SignalerJoueurDTO {
    @Schema(description = "Identifiant du joueur signalé", example = "7")
    private Long idJoueurSignale;
    @Schema(description = "Motif du signalement", example = "Suspicion de triche")
    private String motif;
    @Schema(description = "Description détaillée du signalement", example = "Il semblait connaître mes tuiles")
    private String description;

    public Long getIdJoueurSignale() {
        return idJoueurSignale;
    }

    public void setIdJoueurSignale(Long idJoueurSignale) {
        this.idJoueurSignale = idJoueurSignale;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}