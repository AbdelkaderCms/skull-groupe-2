package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

// Une partie de l'historique d'un membre (on n'expose que ce qui est utile au front)
public class HistoriquePartieDTO {
    @Schema(description = "Identifiant de la partie", example = "12", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Date de la partie", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime date;
    @Schema(description = "Nombre de joueurs dans la partie", example = "4", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer nbJoueurs;
    @Schema(description = "Nombre d'IA dans la partie", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer nbIA;
    @Schema(description = "Pseudo du gagnant (vide si la partie est en cours)", example = "jean123", accessMode = Schema.AccessMode.READ_ONLY)
    private String pseudoGagnant;

    public HistoriquePartieDTO(Long id, LocalDateTime date, Integer nbJoueurs, Integer nbIA, String pseudoGagnant) {
        this.id = id;
        this.date = date;
        this.nbJoueurs = nbJoueurs;
        this.nbIA = nbIA;
        this.pseudoGagnant = pseudoGagnant;
    }

    public Long getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public Integer getNbJoueurs() { return nbJoueurs; }
    public Integer getNbIA() { return nbIA; }
    public String getPseudoGagnant() { return pseudoGagnant; }
}