package skullgroupe2.skullgroupe2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import skullgroupe2.skullgroupe2.entity.Tuile;
import skullgroupe2.skullgroupe2.service.TuileService;

// Controller de Tuile : reçoit les demandes du front et les passe au service
@RestController
@RequestMapping("/tuiles")
public class TuileController {

    private final TuileService service;

    public TuileController(TuileService service) {
        this.service = service;
    }

    // POST /tuiles : crée une tuile face cachée
    @PostMapping
    public Tuile ajouter() {
        return service.creerTuile();
    }

    // GET /tuiles : retourne la liste de toutes les tuiles
    @GetMapping
    public List<Tuile> lister() {
        return service.listerTuiles();
    }

    // GET /tuiles/5 : retourne la tuile numéro 5
    @GetMapping("/{id}")
    public Tuile trouver(@PathVariable Long id) {
        return service.trouverTuile(id);
    }

    // PUT /tuiles/5/retourner : retourne la tuile numéro 5 face visible
    @PutMapping("/{id}/retourner")
    public Tuile retourner(@PathVariable Long id) {
        return service.retournerTuile(id);
    }
}