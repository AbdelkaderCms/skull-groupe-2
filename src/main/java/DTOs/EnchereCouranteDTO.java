package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnchereCouranteDTO {
    @Schema(description="Valeur de l'enchère en cours ", example ="2", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer valeur;
    @Schema(description="Pseudo du joueur qui a fait l'enchère en cours", example ="jean123", accessMode=Schema.AccessMode.READ_ONLY)
    private String pseudoJoueur;
    @Schema(description="Plus petite enchère autorisée", example ="3", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer enchereMinimale;

    public EnchereCouranteDTO(Integer valeur, String pseudoJoueur, Integer enchereMinimale){
        this.valeur=valeur;
        this.pseudoJoueur=pseudoJoueur;
        this.enchereMinimale=enchereMinimale;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public String getPseudoJoueur() {
        return pseudoJoueur;
    }

    public void setPseudoJoueur(String pseudoJoueur) {
        this.pseudoJoueur = pseudoJoueur;
    }

    public Integer getEnchereMinimale() {
        return enchereMinimale;
    }

    public void setEnchereMinimale(Integer enchereMinimale) {
        this.enchereMinimale = enchereMinimale;
    }
}
