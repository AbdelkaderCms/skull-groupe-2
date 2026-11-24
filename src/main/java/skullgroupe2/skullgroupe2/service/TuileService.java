package skullgroupe2.skullgroupe2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import skullgroupe2.skullgroupe2.entity.Tuile;
import skullgroupe2.skullgroupe2.repository.TuileRepository;

// Service de Tuile : contient les règles du jeu liées aux tuiles
@Service
public class TuileService {

    private final TuileRepository tuileRepository;

    public TuileService(TuileRepository tuileRepository) {
        this.tuileRepository = tuileRepository;
    }

    // Crée une tuile posée face cachée (estCachee = true)
    public Tuile creerTuile() {
        Tuile tuile = new Tuile();
        tuile.setEstCachee(true);
        return tuileRepository.save(tuile);
    }

    // Retourne toutes les tuiles enregistrées
    public List<Tuile> listerTuiles() {
        return tuileRepository.findAll();
    }

    // Retrouve une tuile par son id, ou lance une erreur si elle n'existe pas
    public Tuile trouverTuile(Long id) {
        return tuileRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tuile introuvable"));
    }

    // Retourne une tuile face visible : interdit si elle est déjà retournée
    public Tuile retournerTuile(Long id) {
        Tuile tuile = trouverTuile(id); // réutilise la méthode ci-dessus
        if (!tuile.isEstCachee()) {
            throw new IllegalArgumentException("Tuile déjà retournée");
        }
        tuile.setEstCachee(false);
        return tuileRepository.save(tuile); 
    }
}