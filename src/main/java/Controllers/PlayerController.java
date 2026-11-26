package Controllers;

import DTOs.BannirMembreDTO;
import DTOs.SignalementCreeDTO;
import DTOs.SignalementDTO;
import DTOs.SignalerJoueurDTO;
import Entities.SignalementEntity;
import Mappers.SignalementMapper;
import Services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller des joueurs : signalements et bannissement
@RestController
@Tag(name = "Player", description = "Api de gestion des joueurs : signalements et bannissement")
public class PlayerController {

    private final PlayerService service;
    private final SignalementMapper mapper;

    public PlayerController(PlayerService service, SignalementMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // Signaler un joueur
    @Operation(summary = "Signaler un joueur")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Signalement créé"),
        @ApiResponse(responseCode = "400", description = "Champs invalides"),
        @ApiResponse(responseCode = "404", description = "Joueur signalé introuvable")
    })
    @PostMapping("/signalements")
    public ResponseEntity<SignalementCreeDTO> signalerJoueur(@RequestBody SignalerJoueurDTO dto) {
        SignalementEntity signalement = service.signalerJoueur(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCreeDto(signalement));
    }

    // Consulter les signalements (administrateur)
    @Operation(summary = "Consulter les signalements")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste des signalements")
    })
    @GetMapping("/signalements")
    public ResponseEntity<List<SignalementDTO>> consulterSignalements() {
        List<SignalementDTO> signalements = service.consulterSignalements()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(signalements);
    }

    // Bannir un membre (administrateur)
    @Operation(summary = "Bannir un membre")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Membre banni"),
        @ApiResponse(responseCode = "400", description = "Requete invalide"),
        @ApiResponse(responseCode = "404", description = "Membre inexistant")
    })
    @PatchMapping("/membres/{id}")
    public ResponseEntity<Void> bannirMembre(@PathVariable Long id, @RequestBody(required = false) BannirMembreDTO dto) {
        service.bannirMembre(id, dto);
        return ResponseEntity.noContent().build();
    }
}