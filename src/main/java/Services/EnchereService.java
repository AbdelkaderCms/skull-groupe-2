package Services;

import DTOs.CreerEnchereDTO;
import Entities.*;
import Exceptions.*;
import Models.EnchereCouranteData;
import Models.EtatMancheData;
import Repositories.EnchereRepository;
import Repositories.MembreRepository;
import Repositories.PartieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class EnchereService {
    private final PartieRepository partieRepository;
    private final MembreRepository membreRepository;
    private final EnchereRepository enchereRepository;
    public EnchereService(PartieRepository partieRepository, MembreRepository membreRepository, EnchereRepository enchereRepository){
        this.partieRepository=partieRepository;
        this.membreRepository=membreRepository;
        this.enchereRepository=enchereRepository;
    }

    @Transactional
    public EnchereCouranteData consulterEnchereCourante(Long partieId, Integer mancheId) throws NotFoundException {
        PartieEntity partie = this.partieRepository.findById(partieId)
                .orElseThrow(() -> new NotFoundException("Partie inexistante"));
        MancheEntity manche = trouverManche(partie, mancheId);
        EnchereEntity derniere = derniereEnchere(manche);
        return new EnchereCouranteData(derniere, enchereMinimale(derniere));
    }

    @Transactional
    public EtatMancheData encherir(Long partieId, Integer mancheId, String pseudo, CreerEnchereDTO dto) throws InvalidPayloadException, UnauthorizedException, NotFoundException, ForbiddenException, ConflictException {
        if (dto == null || dto.getValeur() == null || dto.getValeur() < 1) {
            throw new InvalidPayloadException("Valeur d'enchère manquante ou invalide");
        }
        if (this.membreRepository.existsByPseudo(pseudo) == false) {
            throw new UnauthorizedException("Joueur inexistant");
        }
        MembreEntity membre = this.membreRepository.findByPseudo(pseudo);
        PartieEntity partie = this.partieRepository.findById(partieId)
                .orElseThrow(() -> new NotFoundException("Partie inexistante"));
        MancheEntity manche = trouverManche(partie, mancheId);

        JoueurEntity joueur = null;
        for (JoueurEntity j : joueursDansLOrdre(partie)) {
            if (memeJoueur(j, membre)) {
                joueur = j;
            }
        }
        if (joueur == null) {
            throw new ForbiddenException("Vous ne participez pas à cette partie");
        }

        boolean phaseOk = manche.getPhase() == PhaseManche.POSE || manche.getPhase() == PhaseManche.ENCHERES;
        if (Boolean.TRUE.equals(manche.getEstEnCours()) == false || phaseOk == false) {
            throw new ConflictException("Enchère impossible : la manche n'est pas en phase d'enchères");
        }
        if (memeJoueur(joueur, manche.getJoueurCourant()) == false) {
            throw new ConflictException("Enchère impossible : ce n'est pas votre tour");
        }
        if (aPasse(manche, joueur)) {
            throw new ConflictException("Enchère impossible : vous vous êtes retiré de la manche");
        }
        int minimum = enchereMinimale(derniereEnchere(manche));
        int totalTuiles = nombreTuilesPosees(manche);
        int valeur = dto.getValeur();
        if (valeur < minimum || valeur > totalTuiles) {
            throw new ConflictException("Enchère impossible : la valeur doit être comprise entre " + minimum + " et " + totalTuiles);
        }

        EnchereEntity enchere = new EnchereEntity(valeur);
        enchere.setJoueurEntity(joueur);
        this.enchereRepository.save(enchere);
        manche.getCoupEntities().add(enchere);

        //Fin des enchères
        JoueurEntity suivant = joueurSuivant(partie, manche, joueur);
        if (valeur == totalTuiles || memeJoueur(suivant, joueur)) {
            manche.setPhase(PhaseManche.RESOLUTION);
            manche.setJoueurEntity(joueur);
            manche.setJoueurCourant(joueur);
        } else {
            manche.setPhase(PhaseManche.ENCHERES);
            manche.setJoueurCourant(suivant);
        }
        return new EtatMancheData(manche, enchere);
    }

    private MancheEntity trouverManche(PartieEntity partie, Integer mancheId) throws NotFoundException {
        if (partie.getMancheEntities() != null) {
            for (MancheEntity manche : partie.getMancheEntities()) {
                if (Objects.equals(manche.getId(), mancheId)) {
                    return manche;
                }
            }
        }
        throw new NotFoundException("Manche inexistante");
    }

    private EnchereEntity derniereEnchere(MancheEntity manche) {
        EnchereEntity derniere = null;
        for (CoupEntity coup : coups(manche)) {
            if (coup instanceof EnchereEntity enchere && (derniere == null || enchere.getId() > derniere.getId())) {
                derniere = enchere;
            }
        }
        return derniere;
    }

    private int enchereMinimale(EnchereEntity derniere) {
        if (derniere == null) {
            return 1;
        }
        return derniere.getValeur() + 1;
    }

    private int nombreTuilesPosees(MancheEntity manche) {
        int nombre = 0;
        for (CoupEntity coup : coups(manche)) {
            if (coup instanceof PoseTuileEntity) {
                nombre++;
            }
        }
        return nombre;
    }

    private boolean aPasse(MancheEntity manche, JoueurEntity joueur) {
        for (CoupEntity coup : coups(manche)) {
            if (coup instanceof PasseEntity && memeJoueur(coup.getJoueurEntity(), joueur)) {
                return true;
            }
        }
        return false;
    }

    private JoueurEntity joueurSuivant(PartieEntity partie, MancheEntity manche, JoueurEntity joueur) {
        List<JoueurEntity> ordre = joueursDansLOrdre(partie);
        int position = 0;
        for (int i = 0; i < ordre.size(); i++) {
            if (memeJoueur(ordre.get(i), joueur)) {
                position = i;
            }
        }
        for (int i = 1; i < ordre.size(); i++) {
            JoueurEntity candidat = ordre.get((position + i) % ordre.size());
            if (aPasse(manche, candidat) == false) {
                return candidat;
            }
        }
        return joueur;
    }

    private List<JoueurEntity> joueursDansLOrdre(PartieEntity partie) {
        List<JoueurEntity> joueurs = new ArrayList<>();
        if (partie.getJoueurs() != null) {
            joueurs.addAll(partie.getJoueurs());
        }
        joueurs.sort(Comparator.comparing(JoueurEntity::getId));
        return joueurs;
    }

    private List<CoupEntity> coups(MancheEntity manche) {
        List<CoupEntity> coups = new ArrayList<>();
        if (manche.getCoupEntities() != null) {
            coups.addAll(manche.getCoupEntities());
        }
        return coups;
    }

    private boolean memeJoueur(JoueurEntity a, JoueurEntity b) {
        return a != null && b != null && Objects.equals(a.getId(), b.getId());
    }
}
