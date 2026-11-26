package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

public class EtatMancheDTO {
    @Schema(description="Identifiant de la manche", example ="5", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer mancheId;
    @Schema(description="Phase de la manche", example ="ENCHERES", accessMode=Schema.AccessMode.READ_ONLY)
    private String phase;
    @Schema(description="Valeur de l'enchère en cours", example ="3", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer enchereCourante;
    @Schema(description="Pseudo du joueur qui a fait l'enchère en cours", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoEncherisseur;
    @Schema(description="Pseudo du joueur dont c'est le tour", example ="bob45", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoJoueurCourant;
    @Schema(description="Pseudo du challenger", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoChallenger;

    public EtatMancheDTO(Integer mancheId, String phase, Integer enchereCourante, String pseudoEncherisseur,
                         String pseudoJoueurCourant, String pseudoChallenger){
        this.mancheId=mancheId;
        this.phase=phase;
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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
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
