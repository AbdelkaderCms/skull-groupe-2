package Controllers;

import DTOs.CreerPartieDTO;
import DTOs.CreerPartieResponseDTO;
import Mappers.CreerPartieResponseMapper;
import Services.GameService;
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
    public GameController(GameService service,CreerPartieResponseMapper mapper){
        this.service=service;
        this.mapper=mapper;
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

    //Enchérir
    //Poser une tuile
    // Révéler une tuile
    //Passer son tour

    //Quitter une partie en cours

    //Fin de la manche
    //Fin de partie
}
