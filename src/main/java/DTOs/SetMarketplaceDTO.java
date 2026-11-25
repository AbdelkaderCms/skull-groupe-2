package DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class SetMarketplaceDTO {
    @Schema(description="Identifiant du set", example ="3", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description="Prix du set", example ="12", accessMode=Schema.AccessMode.READ_ONLY)
    private Integer prix;
    @Schema (description="Le joueur possède le set", example ="true", accessMode=Schema.AccessMode.READ_ONLY)
    private boolean possede;
    @Schema (description="Le joueur a ce set pour set courant", example ="false", accessMode=Schema.AccessMode.READ_ONLY)
    private boolean courant;

    public SetMarketplaceDTO(Integer id, Integer prix, Boolean possede, Boolean courant){
        this.id=id;
        this.prix=prix;
        this.courant=courant;
        this.possede=possede;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getPrix() {
        return prix;
    }

    public boolean isCourant() {
        return courant;
    }

    public void setCourant(boolean courant) {
        this.courant = courant;
    }

    public boolean isPossede() {
        return possede;
    }

    public void setPossede(boolean possede) {
        this.possede = possede;
    }
}
