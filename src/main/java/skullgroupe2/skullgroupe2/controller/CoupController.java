package skullgroupe2.skullgroupe2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import skullgroupe2.skullgroupe2.entity.Coup;
import skullgroupe2.skullgroupe2.service.CoupService;

// Controller de Coup : reçoit les demandes du front et les passe au service
@RestController
@RequestMapping("/coups")
public class CoupController {

    private final CoupService service;

    public CoupController(CoupService service) {
        this.service = service;
    }

    // GET /coups : retourne la liste de tous les coups
    @GetMapping
    public List<Coup> lister() {
        return service.listerCoups();
    }

    // GET /coups/5 : retourne le coup numéro 5
    @GetMapping("/{id}")
    public Coup trouver(@PathVariable Long id) {
        return service.trouverCoup(id);
    }
}