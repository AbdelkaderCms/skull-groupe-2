package Controllers;

import DTOs.HistoriquePartieDTO;
import DTOs.ScoreDTO;
import Mappers.LadderMapper;
import Services.LadderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller du Ladder : historique des parties et tableau des scores
@RestController
@RequestMapping("api/membres")
@Tag(name = "Ladder", description = "Historique des parties et tableau des scores")
public class LadderController {

    private final LadderService service;
    private final LadderMapper mapper;

    public LadderController(LadderService service, LadderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // GET api/membres/5/historique : les 5 dernières parties du membre 5
    @Operation(summary = "Consulter l'historique (5 dernières parties) d'un membre")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Historique trouvé"),
        @ApiResponse(responseCode = "204", description = "Aucune partie dans l'historique")
    })
    @GetMapping("/{id}/historique")
    public ResponseEntity<List<HistoriquePartieDTO>> consulterHistorique(@PathVariable Long id) {
        List<HistoriquePartieDTO> historique = service.consulterHistorique(id)
                .stream()
                .map(mapper::toHistoriqueDto)
                .toList();
        if (historique.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 : le diagramme demande "Historique vide"
        }
        return ResponseEntity.ok(historique);
    }

    // GET api/membres/scores : le classement des membres par points
    @Operation(summary = "Consulter le tableau des scores")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tableau des scores")
    })
    @GetMapping("/scores")
    public ResponseEntity<List<ScoreDTO>> consulterScores() {
        List<ScoreDTO> scores = service.consulterScores()
                .stream()
                .map(mapper::toScoreDto)
                .toList();
        return ResponseEntity.ok(scores);
    }
}