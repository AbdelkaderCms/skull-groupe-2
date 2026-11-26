package Controllers;

import DTOs.CreerEnchereDTO;
import DTOs.EnchereCouranteDTO;
import DTOs.EtatMancheDTO;
import Mappers.EnchereMapper;
import Services.EnchereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/game")
@Tag(name="Game",description="Api de gestion d'une partie de skull")
public class EnchereController {
    private final EnchereService service;
    private final EnchereMapper mapper;
    public EnchereController(EnchereService service,EnchereMapper mapper){
        this.service=service;
        this.mapper=mapper;
    }

    //Consulter l'enchère en cours
    @Operation(summary="Consulter l'enchère en cours et l'enchère minimale possible")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "Partie ou manche inexistante")
    })
    @GetMapping("/{partieId}/manches/{mancheId}/encheres/courante")
    public ResponseEntity<EnchereCouranteDTO> consulterEnchereCourante(@PathVariable Long partieId,
                                                                       @PathVariable Integer mancheId){
        EnchereCouranteDTO dto = mapper.toDto(service.consulterEnchereCourante(partieId, mancheId));
        return ResponseEntity.ok().body(dto);
    }

    //Enchérir
    @Operation(summary="Enchérir ou surenchérir")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Enchère enregistrée"),
        @ApiResponse(responseCode = "400", description = "Valeur manquante ou invalide"),
        @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
        @ApiResponse(responseCode = "403", description = "Le joueur ne participe pas à cette partie"),
        @ApiResponse(responseCode = "404", description = "Partie ou manche inexistante"),
        @ApiResponse(responseCode = "409", description = "Enchère impossible (pas son tour, valeur trop basse ou trop haute, joueur retiré)")
    })
    @PostMapping("/{partieId}/manches/{mancheId}/encheres")
    public ResponseEntity<EtatMancheDTO> encherir(@PathVariable Long partieId, @PathVariable Integer mancheId,
                                                  @RequestParam String pseudo, @RequestBody CreerEnchereDTO dto){
        EtatMancheDTO etat = mapper.toDto(service.encherir(partieId, mancheId, pseudo, dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(etat);
    }
}
