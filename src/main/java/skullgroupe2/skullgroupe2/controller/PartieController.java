package skullgroupe2.skullgroupe2.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // POST /parties?nbJoueurs=3&nbIA=1 : crée une partie
    @PostMapping 
    public Partie ajouter(@RequestParam Integer nbJoueurs, @RequestParam Integer nbIA){
        return service.creerPartie(nbJoueurs, nbIA);
    }


    // GET /parties : retourne la liste de toutes les parties
    @GetMapping 
    public List<Partie> lister(){
        return service.listerParties();
    }

    // GET /parties/5 : retourne la partie numéro 5
    @GetMapping("/{id}")
    public Partie trouver(@PathVariable Long id){
        return service.trouverPartie(id);
    }


    // GET /parties/historique : retourne les parties de la plus récente à la plus ancienne
    @GetMapping("/historique")
    public List<Partie> historique() {
        return service.historique();
    }

    // DELETE /parties/5 : supprime la partie numéro 5
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        service.supprimerPartie(id);
    }
}
