package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Représentation d'une enchère")
public class CreerEnchereDTO {
    @Schema(description="Nombre de fleurs que le joueur pense pouvoir retourner", example ="3")
    private Integer valeur;

    public CreerEnchereDTO(){}
    public CreerEnchereDTO(Integer valeur){
        this.valeur=valeur;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
}
