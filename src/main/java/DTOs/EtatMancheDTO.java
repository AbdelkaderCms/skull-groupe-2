package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

public class EtatMancheDTO {
    @Schema(description="Identifiant de la manche", example ="5", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer mancheId;
    @Schema(description="Enchère la plus haute", example ="3", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer enchereCourante;
    @Schema(description="Pseudo du joueur qui a fait cette enchère", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoEncherisseur;
    @Schema(description="Pseudo du joueur dont c'est le tour", example ="bob45", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoJoueurCourant;
    @Schema(description="Pseudo du challenger (null tant que les enchères continuent)", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoChallenger;

    public EtatMancheDTO(Integer mancheId, Integer enchereCourante, String pseudoEncherisseur, String pseudoJoueurCourant, String pseudoChallenger){
        this.mancheId=mancheId;
        this.enchereCourante=enchereCourante;
        this.pseudoEncherisseur=pseudoEncherisseur;
        this.pseudoJoueurCourant=pseudoJoueurCourant;
        this.pseudoChallenger=pseudoChallenger;
    }

    public Integer getMancheId() {
        return mancheId;
    }

    public void setMancheId(Integer mancheId) {
        this.mancheId = mancheId;
    }

    public Integer getEnchereCourante() {
        return enchereCourante;
    }

    public void setEnchereCourante(Integer enchereCourante) {
        this.enchereCourante = enchereCourante;
    }

    public String getPseudoEncherisseur() {
        return pseudoEncherisseur;
    }

    public void setPseudoEncherisseur(String pseudoEncherisseur) {
        this.pseudoEncherisseur = pseudoEncherisseur;
    }

    public String getPseudoJoueurCourant() {
        return pseudoJoueurCourant;
    }

    public void setPseudoJoueurCourant(String pseudoJoueurCourant) {
        this.pseudoJoueurCourant = pseudoJoueurCourant;
    }

    public String getPseudoChallenger() {
        return pseudoChallenger;
    }

    public void setPseudoChallenger(String pseudoChallenger) {
        this.pseudoChallenger = pseudoChallenger;
    }
}
