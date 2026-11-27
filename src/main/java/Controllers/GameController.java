package Controllers;

import DTOs.CreerEnchereDTO;
import DTOs.CreerPartieDTO;
import DTOs.CreerPartieResponseDTO;
import DTOs.EnchereCouranteDTO;
import DTOs.EtatMancheDTO;
import Mappers.CreerPartieResponseMapper;
import Mappers.EnchereMapper;
import Services.EnchereService;
import Services.GameService;
import Services.QuitterPartieService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/game")
@Tag(name="Game",description="Api de gestion d'une partie de skull")
public class GameController {
    private final GameService service;
    private final CreerPartieResponseMapper mapper;
    private final EnchereService enchereService;
    private final EnchereMapper enchereMapper;
    private final QuitterPartieService quitterPartieService;
    public GameController(GameService service,CreerPartieResponseMapper mapper,EnchereService enchereService,EnchereMapper enchereMapper,QuitterPartieService quitterPartieService){
        this.service=service;
        this.mapper=mapper;
        this.enchereService=enchereService;
        this.enchereMapper=enchereMapper;
        this.quitterPartieService=quitterPartieService;
    }


    //Créer une partie
    @Operation(summary="Créer une nouvelle partie")
    @ApiResponses ({
        @ApiResponse(responseCode = "400",description ="Champs invalides ou manquants"),
        @ApiResponse(responseCode = "201", description="Partie crée")
    })
    @PostMapping
    public ResponseEntity<CreerPartieResponseDTO> creerPartie(@RequestBody CreerPartieDTO dto,@RequestParam String pseudo){
        CreerPartieResponseDTO responseDto=mapper.toDto(service.creePartie(dto,pseudo));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    //Rejoindre une partie
    //Démarrer une partie

    //Consulter l'enchère en cours
    @Operation(summary="Consulter l'enchère la plus haute de la manche")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK (enchère à 0 si aucune enchère)"),
        @ApiResponse(responseCode = "404", description = "Partie ou manche inexistante")
    })
    @GetMapping("/{partieId}/manches/{mancheId}/encheres/courante")
    public ResponseEntity<EnchereCouranteDTO> consulterEnchereCourante(@PathVariable Long partieId, @PathVariable Integer mancheId){
        EnchereCouranteDTO dto = enchereMapper.toDto(enchereService.consulterEnchereCourante(partieId, mancheId));
        return ResponseEntity.ok().body(dto);
    }

    //Enchérir
    @Operation(summary="Enchérir ou surenchérir")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Enchère enregistrée"),
        @ApiResponse(responseCode = "400", description = "Valeur manquante"),
        @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
        @ApiResponse(responseCode = "403", description = "Le joueur ne participe pas à cette partie ou s'est retiré de la manche"),
        @ApiResponse(responseCode = "404", description = "Partie ou manche inexistante"),
        @ApiResponse(responseCode = "409", description = "Enchère impossible (pas son tour, valeur invalide, enchères terminées)")
    })
    @PostMapping("/{partieId}/manches/{mancheId}/encheres")
    public ResponseEntity<EtatMancheDTO> encherir(@PathVariable Long partieId, @PathVariable Integer mancheId,
                                                  @RequestParam String pseudo, @RequestBody CreerEnchereDTO dto){
        EtatMancheDTO etat = enchereMapper.toDto(enchereService.encherir(partieId, mancheId, pseudo, enchereMapper.toEntity(dto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(etat);
    }
    //Poser une tuile
    // Révéler une tuile
    //Passer son tour

    //Quitter une partie en cours
    @Operation(summary="Quitter une partie (une IA jouera à la place du joueur si la partie a commencé)")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Partie quittée"),
        @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
        @ApiResponse(responseCode = "404", description = "Partie inexistante ou joueur absent de la partie"),
        @ApiResponse(responseCode = "409", description = "Partie terminée")
    })
    @DeleteMapping("/{partieId}/joueurs")
    public ResponseEntity<Void> quitterPartie(@PathVariable Long partieId, @RequestParam String pseudo){
        this.quitterPartieService.quitterPartie(partieId, pseudo);
        return ResponseEntity.noContent().build();
    }

    //Fin de la manche
    //Fin de partie
}
