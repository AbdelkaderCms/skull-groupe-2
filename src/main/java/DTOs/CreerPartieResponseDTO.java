package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

public class CreerPartieResponseDTO {
    @Schema(description="Identifiant de la partie crée", example ="12", accessMode=Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Joueurs et IA présents dans la partie", example="{jean123,bob45,alice6}", accessMode =Schema.AccessMode.READ_ONLY)
    private Set<String> pseudoJoueurs;
    @Schema(description="Nombre de joueurs dans la partie", example ="4", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer nbJoueurs;
    @Schema(description = "Nombre d'IA dans la partie", example="1", accessMode =Schema.AccessMode.READ_ONLY)
    private Integer nbIA;

    public CreerPartieResponseDTO(Long id, Set<String> pseudoJoueurs, Integer nbJoueurs, Integer nbIa){
        this.id=id;
        this.nbIA=nbIa;
        this.pseudoJoueurs=pseudoJoueurs;
        this.nbJoueurs=nbJoueurs;
    }

    public Integer getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(Integer nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbIA() {
        return nbIA;
    }

    public void setNbIA(Integer nbIA) {
        this.nbIA = nbIA;
    }

    public Set<String> getPseudoJoueurs() {
        return pseudoJoueurs;
    }

    public void setPseudoJoueurs(Set<String> pseudoJoueurs) {
        this.pseudoJoueurs = pseudoJoueurs;
    }
}
