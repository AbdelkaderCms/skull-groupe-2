package Controllers;

import Services.QuitterPartieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/game")
@Tag(name="Game",description="Api de gestion d'une partie de skull")
public class QuitterPartieController {
    private final QuitterPartieService service;
    public QuitterPartieController(QuitterPartieService service){
        this.service=service;
    }

    //Quitter une partie
    @Operation(summary="Quitter une partie (une IA prend la place si la partie a commencé)")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Partie quittée"),
        @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
        @ApiResponse(responseCode = "404", description = "Partie inexistante ou joueur absent de la partie"),
        @ApiResponse(responseCode = "409", description = "Partie terminée ou aucune IA disponible")
    })
    @DeleteMapping("/{partieId}/joueurs")
    public ResponseEntity<Void> quitterPartie(@PathVariable Long partieId, @RequestParam String pseudo){
        this.service.quitterPartie(partieId, pseudo);
        return ResponseEntity.noContent().build();
    }
}
