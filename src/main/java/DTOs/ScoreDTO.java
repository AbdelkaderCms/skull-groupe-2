package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

// Une ligne du tableau des scores : pseudo et points seulement (jamais l'email ni le mot de passe)
public class ScoreDTO {
    @Schema(description = "Pseudo du membre", example = "jean123", accessMode = Schema.AccessMode.READ_ONLY)
    private String pseudo;
    @Schema(description = "Points du membre", example = "250", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer points;

    public ScoreDTO(String pseudo, Integer points) {
        this.pseudo = pseudo;
        this.points = points;
    }

    public String getPseudo() { return pseudo; }
    public Integer getPoints() { return points; }
}