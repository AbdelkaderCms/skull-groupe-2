package skullgroupe2.skullgroupe2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import skullgroupe2.skullgroupe2.entity.Coup;
import skullgroupe2.skullgroupe2.exception.RessourceIntrouvableException;
import skullgroupe2.skullgroupe2.repository.CoupRepository;

// Service de Coup : lecture des coups (la création viendra avec Passe, Enchere et PoseTuile)
@Service
public class CoupService {

    private final CoupRepository coupRepository;

    public CoupService(CoupRepository coupRepository) {
        this.coupRepository = coupRepository;
    }

    // Retourne tous les coups enregistrés
    public List<Coup> listerCoups() {
        return coupRepository.findAll();
    }

    // Retrouve un coup par son id, ou lance une erreur s'il n'existe pas
    public Coup trouverCoup(Long id) {
        return coupRepository.findById(id)
            .orElseThrow(() -> new RessourceIntrouvableException("Tuile introuvable"));
    }
}