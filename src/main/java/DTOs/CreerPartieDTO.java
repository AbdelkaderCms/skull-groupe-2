package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Représentation d'une partie")
public class CreerPartieDTO {
    @Schema(description="Nombre de joueurs dans la partie", example ="4", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer nbJoueurs;
    @Schema(description = "Nombre d'IA dans la partie", example="1", accessMode =Schema.AccessMode.READ_ONLY)
    private Integer nbIA;

    public CreerPartieDTO(Integer nbIA, Integer nbJoueurs){
        this.nbIA=nbIA;
        this.nbJoueurs=nbJoueurs;
    }

    public Integer getNbIA() {
        return nbIA;
    }

    public void setNbIA(Integer nbIA) {
        this.nbIA = nbIA;
    }

    public Integer getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(Integer nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }
}
