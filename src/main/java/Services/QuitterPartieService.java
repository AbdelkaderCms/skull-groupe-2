package Services;

import Entities.*;
import Exceptions.ConflictException;
import Exceptions.NotFoundException;
import Exceptions.UnauthorizedException;
import Repositories.EvenementRepository;
import Repositories.JoueurRepository;
import Repositories.MembreRepository;
import Repositories.PartieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class QuitterPartieService {
    private final PartieRepository partieRepository;
    private final MembreRepository membreRepository;
    private final JoueurRepository joueurRepository;
    private final EvenementRepository evenementRepository;
    public QuitterPartieService(PartieRepository partieRepository, MembreRepository membreRepository, JoueurRepository joueurRepository, EvenementRepository evenementRepository){
        this.partieRepository=partieRepository;
        this.membreRepository=membreRepository;
        this.joueurRepository=joueurRepository;
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

        Set<JoueurEntity> joueurs = partie.getJoueurs();
        if (joueurs == null || joueurs.stream().noneMatch(j -> memeJoueur(j, membre))) {
            throw new NotFoundException("Joueur absent de cette partie");
        }
        if (partie.getGagnant() != null) {
            throw new ConflictException("Partie terminée");
        }

        //Partie pas encore commencée
        if (partie.getMancheEntities() == null || partie.getMancheEntities().isEmpty()) {
            joueurs.removeIf(j -> memeJoueur(j, membre));
            return;
        }

        //Partie en cours : une IA prend la place
        JoueurEntity ia = null;
        for (JoueurEntity candidat : this.joueurRepository.findAll()) {
            if (ia == null && candidat instanceof IAEntity && joueurs.stream().noneMatch(j -> memeJoueur(j, candidat))) {
                ia = candidat;
            }
        }
        if (ia == null) {
            throw new ConflictException("Aucune IA disponible pour remplacer le joueur");
        }

        this.evenementRepository.save(new EvenementEntity(membre, partie, dernierCoup(partie)));
        joueurs.removeIf(j -> memeJoueur(j, membre));
        partie.ajoutJoueur(ia);

        for (MancheEntity manche : partie.getMancheEntities()) {
            if (Boolean.TRUE.equals(manche.getEstEnCours())) {
                if (manche.getCoupEntities() != null) {
                    for (CoupEntity coup : manche.getCoupEntities()) {
                        if (memeJoueur(coup.getJoueurEntity(), membre)) {
                            coup.setJoueurEntity(ia);
                        }
                    }
                }
                if (memeJoueur(manche.getJoueurCourant(), membre)) {
                    manche.setJoueurCourant(ia);
                }
                if (memeJoueur(manche.getJoueurEntity(), membre)) {
                    manche.setJoueurEntity(ia);
                }
            }
        }
    }

    private CoupEntity dernierCoup(PartieEntity partie) {
        CoupEntity dernier = null;
        for (MancheEntity manche : partie.getMancheEntities()) {
            if (manche.getCoupEntities() != null) {
                for (CoupEntity coup : manche.getCoupEntities()) {
                    if (dernier == null || coup.getId() > dernier.getId()) {
                        dernier = coup;
                    }
                }
            }
        }
        return dernier;
    }

    private boolean memeJoueur(JoueurEntity a, JoueurEntity b) {
        return a != null && b != null && Objects.equals(a.getId(), b.getId());
    }
}
