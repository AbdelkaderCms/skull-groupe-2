package Controllers;

import DTOs.SetMarketplaceDTO;
import Entities.SetEntity;
import Mappers.SetMarketPlaceMapper;
import Models.SetMarketplaceData;
import Services.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/market")
@Tag(name="Market",description="Api de gestion du marketplace pour l'achat d'aspects")
public class MarketController {
    private final MarketService service;
    private final SetMarketPlaceMapper mapper;
    public MarketController(MarketService service, SetMarketPlaceMapper mapper){
        this.service=service;
        this.mapper=mapper;
    }

    //Consulter le marketplace
    @Operation(summary="Consulter le marketplace")
    @ApiResponses({
        @ApiResponse(responseCode = "401",description ="Utilisateur non connecté"),
        @ApiResponse(responseCode = "200",description ="OK")
    })
    @GetMapping
    public ResponseEntity<List<SetMarketplaceDTO>> consulterMarketplace(@RequestParam String pseudo){
        List<SetMarketplaceData> sets = service.consulterMarketplace(pseudo);
        List<SetMarketplaceDTO> setsDto = new ArrayList<>(List.of());
        for (SetMarketplaceData set: sets){
            setsDto.add(mapper.toDto(set));
        }

        return ResponseEntity.ok().body(setsDto);
    }

    //Acheter un aspect
    @Operation(summary="acheter un aspect")
    @ApiResponses({
        @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
        @ApiResponse(responseCode = "409", description = "Aspect déjà possédé ou solde insuffisant"),
        @ApiResponse(responseCode = "200", description = "Aspect acheté"),
        @ApiResponse(responseCode = "404", description = "Aspect inexistant"),
        @ApiResponse(responseCode = "400", description = "Payload incomplet")
    })
    @PatchMapping
    public ResponseEntity<Void> acheterAspect(@RequestParam Integer aspectID, @RequestParam String pseudo){
        this.service.acheterAspect(aspectID, pseudo);
        return ResponseEntity.ok().build();
    }

    //Définir l'aspect courant
    @Operation(summary="définir l'aspect courant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aspect défini"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non connecté"),
            @ApiResponse(responseCode = "404", description = "Aspect inexistant"),
            @ApiResponse(responseCode = "403", description = "Aspect non possédé"),
            @ApiResponse(responseCode = "400", description = "Payload incomplet")


    })
    @PatchMapping
    public ResponseEntity<Void> changerAspectCourant(@RequestParam Integer aspectID, @RequestParam String pseudo){
        this.service.definirAspectCourant(aspectID,pseudo);
        return ResponseEntity.ok().build();
    }
}
