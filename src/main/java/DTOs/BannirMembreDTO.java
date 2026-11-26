package DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;

// Corps de la requête PATCH /membres/{id}
public class BannirMembreDTO {
    @Schema(description = "true pour bannir le membre", example = "true")
    @JsonAlias("EstBanni") 
    private Boolean estBanni;

    public Boolean getEstBanni() {
        return estBanni;
    }

    public void setEstBanni(Boolean estBanni) {
        this.estBanni = estBanni;
    }
}