package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnchereCouranteDTO {
    @Schema(description="Enchère la plus haute déjà faite dans la manche (0 si aucune enchère)", example ="2", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer enchereCourante;
    @Schema(description="Pseudo du joueur qui a fait cette enchère", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoJoueur;

    public EnchereCouranteDTO(Integer enchereCourante, String pseudoJoueur){
        this.enchereCourante=enchereCourante;
        this.pseudoJoueur=pseudoJoueur;
    }

    public Integer getEnchereCourante() {
        return enchereCourante;
    }

    public void setEnchereCourante(Integer enchereCourante) {
        this.enchereCourante = enchereCourante;
    }

    public String getPseudoJoueur() {
        return pseudoJoueur;
    }

    public void setPseudoJoueur(String pseudoJoueur) {
        this.pseudoJoueur = pseudoJoueur;
    }
}
