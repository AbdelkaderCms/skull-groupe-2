package Models;

public class EtatMancheData {
    private Integer mancheId;
    private Integer valeur;
    private String pseudoEncherisseur;
    private String pseudoChallenger;

    public EtatMancheData(Integer mancheId, Integer valeur, String pseudoEncherisseur, String pseudoChallenger){
        this.mancheId = mancheId;
        this.valeur = valeur;
        this.pseudoEncherisseur = pseudoEncherisseur;
        this.pseudoChallenger = pseudoChallenger;
    }

    public Integer getMancheId() {
        return mancheId;
    }

    public Integer getValeur() {
        return valeur;
    }

    public String getPseudoEncherisseur() {
        return pseudoEncherisseur;
    }

    public String getPseudoChallenger() {
        return pseudoChallenger;
    }
}
