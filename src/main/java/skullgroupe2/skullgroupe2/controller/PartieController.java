package skullgroupe2.skullgroupe2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import skullgroupe2.skullgroupe2.entity.Partie;
import skullgroupe2.skullgroupe2.service.PartieService;
import java.util.List;

// Controller de Partie : reçoit les demandes du front et les passe au service
@RestController 
@RequestMapping("/parties")
public class PartieController {
    
    private final PartieService service;

    public PartieController(PartieService service){
        this.service = service;
    }

    @PostMapping 
    public Partie ajouter(@RequestParam Integer nbJoueurs, @RequestParam Integer nbIA){
        return service.creerPartie(nbJoueurs, nbIA);
    }


    @GetMapping 
    public List<Partie> lister(){
        return service.listerParties();
    }
}
