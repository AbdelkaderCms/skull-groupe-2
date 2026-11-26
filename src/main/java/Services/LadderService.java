package Services;

import Entities.MembreEntity;
import Entities.PartieEntity;
import Repositories.MembreRepository;
import Repositories.PartieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service du Ladder : historique des parties et tableau des scores
@Service
public class LadderService {

    private final PartieRepository partieRepository;
    private final MembreRepository membreRepository;

    public LadderService(PartieRepository partieRepository, MembreRepository membreRepository) {
        this.partieRepository = partieRepository;
        this.membreRepository = membreRepository;
    }

    // Retourne les 5 dernières parties d'un membre (liste vide s'il n'en a aucune)
    public List<PartieEntity> consulterHistorique(Long idMembre) {
        return partieRepository.findTop5ByJoueurs_IdOrderByDateDesc(idMembre);
    }

    // Retourne tous les membres classés du plus de points au moins de points
    public List<MembreEntity> consulterScores() {
        return membreRepository.findAllByOrderByPointsDesc();
    }
}