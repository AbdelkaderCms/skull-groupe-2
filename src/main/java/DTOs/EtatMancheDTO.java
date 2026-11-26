package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

public class EtatMancheDTO {
    @Schema(description="Identifiant de la manche", example ="5", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer mancheId;
    @Schema(description="Valeur de l'enchère en cours", example ="3", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer enchereCourante;
    @Schema(description="Pseudo du joueur qui a fait l'enchère en cours", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoEncherisseur;
    @Schema(description="Les enchères sont terminées", example ="false", accessMode=Schema.AccessMode.READ_ONLY)
    private boolean encheresTerminees;
    @Schema(description="Pseudo du challenger", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoChallenger;

    public EtatMancheDTO(Integer mancheId, Integer enchereCourante, String pseudoEncherisseur, boolean encheresTerminees, String pseudoChallenger){
        this.mancheId=mancheId;
        this.enchereCourante=enchereCourante;
        this.pseudoEncherisseur=pseudoEncherisseur;
        this.encheresTerminees=encheresTerminees;
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

    public boolean isEncheresTerminees() {
        return encheresTerminees;
    }

    public void setEncheresTerminees(boolean encheresTerminees) {
        this.encheresTerminees = encheresTerminees;
    }

    public String getPseudoChallenger() {
        return pseudoChallenger;
    }

    public void setPseudoChallenger(String pseudoChallenger) {
        this.pseudoChallenger = pseudoChallenger;
    }
}
