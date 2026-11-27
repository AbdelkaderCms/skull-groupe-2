package Services;

import Entities.*;
import Exceptions.ConflictException;
import Exceptions.NotFoundException;
import Exceptions.UnauthorizedException;
import Repositories.EvenementRepository;
import Repositories.MembreRepository;
import Repositories.PartieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class QuitterPartieService {
    private final PartieRepository partieRepository;
    private final MembreRepository membreRepository;
    private final EvenementRepository evenementRepository;
    public QuitterPartieService(PartieRepository partieRepository, MembreRepository membreRepository, EvenementRepository evenementRepository){
        this.partieRepository=partieRepository;
        this.membreRepository=membreRepository;
        this.evenementRepository=evenementRepository;
    }

    @Transactional
    public void quitterPartie(Long partieId, String pseudo) throws UnauthorizedException, NotFoundException, ConflictException {
        if (this.membreRepository.existsByPseudo(pseudo) == false) {
            throw new UnauthorizedException("Joueur inexistant");
        }
        MembreEntity membre = this.membreRepository.findByPseudo(pseudo);
        PartieEntity partie = this.partieRepository.findById(partieId)
                .orElseThrow(() -> new NotFoundException("Partie inexistante"));

        if (partie.getJoueurs().stream().noneMatch(j -> j.getId().equals(membre.getId()))) {
            throw new NotFoundException("Joueur absent de cette partie");
        }
        if (partie.getGagnant() != null) {
            throw new ConflictException("Partie terminée");
        }

        //Partie pas encore commencée
        if (partie.getMancheEntities().isEmpty()) {
            partie.getJoueurs().removeIf(j -> j.getId().equals(membre.getId()));
            return;
        }

        //Partie en cours : l'IA jouera à sa place
        this.evenementRepository.save(new EvenementEntity(membre, dernierCoup(partie)));
    }

    private CoupEntity dernierCoup(PartieEntity partie) {
        CoupEntity dernier = null;
        for (MancheEntity manche : partie.getMancheEntities()) {
            for (CoupEntity coup : manche.getCoupEntities()) {
                if (dernier == null || coup.getId() > dernier.getId()) {
                    dernier = coup;
                }
            }
        }
        return dernier;
    }
}
