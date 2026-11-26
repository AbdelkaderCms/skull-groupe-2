package Models;

public class EnchereCouranteData {
    private Integer valeur;
    private String pseudoJoueur;
    private int enchereMinimale;

    public EnchereCouranteData(Integer valeur, String pseudoJoueur, int enchereMinimale){
        this.valeur = valeur;
        this.pseudoJoueur = pseudoJoueur;
        this.enchereMinimale = enchereMinimale;
    }

    public Integer getValeur() {
        return valeur;
    }

    public String getPseudoJoueur() {
        return pseudoJoueur;
    }

    public int getEnchereMinimale() {
        return enchereMinimale;
    }
}
