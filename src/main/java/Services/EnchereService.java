package Services;

import Entities.*;
import Exceptions.*;
import Models.EtatMancheData;
import Repositories.EnchereRepository;
import Repositories.MancheRepository;
import Repositories.MembreRepository;
import Repositories.PartieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EnchereService {
    private final PartieRepository partieRepository;
    private final MembreRepository membreRepository;
    private final MancheRepository mancheRepository;
    private final EnchereRepository enchereRepository;
    public EnchereService(PartieRepository partieRepository, MembreRepository membreRepository, MancheRepository mancheRepository, EnchereRepository enchereRepository){
        this.partieRepository=partieRepository;
        this.membreRepository=membreRepository;
        this.mancheRepository=mancheRepository;
        this.enchereRepository=enchereRepository;
    }

    @Transactional
    public EnchereEntity consulterEnchereCourante(Long partieId, Integer mancheId) throws NotFoundException {
        MancheEntity manche = trouverManche(trouverPartie(partieId), mancheId);
        return derniereEnchere(manche);
    }

    @Transactional
    public EtatMancheData encherir(Long partieId, Integer mancheId, String pseudo, EnchereEntity enchere) throws InvalidPayloadException, UnauthorizedException, NotFoundException, ForbiddenException, ConflictException {
        if (enchere.getValeur() == null) {
            throw new InvalidPayloadException("Valeur d'enchère manquante");
        }
        if (this.membreRepository.existsByPseudo(pseudo) == false) {
            throw new UnauthorizedException("Joueur inexistant");
        }
        MembreEntity membre = this.membreRepository.findByPseudo(pseudo);
        PartieEntity partie = trouverPartie(partieId);
        MancheEntity manche = trouverManche(partie, mancheId);

        if (partie.getJoueurs().stream().noneMatch(j -> j.getId().equals(membre.getId()))) {
            throw new ForbiddenException("Vous ne participez pas à cette partie");
        }
        if (aPasse(manche, membre)) {
            throw new ForbiddenException("Vous vous êtes retiré de cette manche");
        }
        if (Boolean.TRUE.equals(manche.getEstEnCours()) == false || manche.getJoueurEntity() != null) {
            throw new ConflictException("Enchère impossible : les enchères de cette manche sont terminées");
        }
        if (manche.getJoueurCourant() == null || manche.getJoueurCourant().getId().equals(membre.getId()) == false) {
            throw new ConflictException("Enchère impossible : ce n'est pas votre tour");
        }

        EnchereEntity derniere = derniereEnchere(manche);
        int enchereCourante = 0;
        if (derniere != null) {
            enchereCourante = derniere.getValeur();
        }
        int tuilesPosees = nombreTuilesPosees(manche);
        if (enchere.getValeur() <= enchereCourante || enchere.getValeur() > tuilesPosees) {
            throw new ConflictException("Enchère impossible : la valeur doit être comprise entre " + (enchereCourante + 1) + " et " + tuilesPosees);
        }

        enchere.setMembreEntity(membre);
        this.enchereRepository.save(enchere);
        manche.getCoupEntities().add(enchere);

        //Fin des enchères
        if (enchere.getValeur() == tuilesPosees) {
            manche.setJoueurEntity(membre);
            manche.setJoueurCourant(membre);
        } else {
            manche.setJoueurCourant(joueurSuivant(partie, manche, membre));
        }
        this.mancheRepository.save(manche);
        return new EtatMancheData(manche, enchere);
    }

    private PartieEntity trouverPartie(Long partieId) throws NotFoundException {
        return this.partieRepository.findById(partieId)
                .orElseThrow(() -> new NotFoundException("Partie inexistante"));
    }

    private MancheEntity trouverManche(PartieEntity partie, Integer mancheId) throws NotFoundException {
        return partie.getMancheEntities().stream()
                .filter(m -> m.getId().equals(mancheId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Manche inexistante"));
    }

    private EnchereEntity derniereEnchere(MancheEntity manche) {
        EnchereEntity derniere = null;
        for (CoupEntity coup : manche.getCoupEntities()) {
            if (coup instanceof EnchereEntity enchere && (derniere == null || enchere.getId() > derniere.getId())) {
                derniere = enchere;
            }
        }
        return derniere;
    }

    private int nombreTuilesPosees(MancheEntity manche) {
        int nombre = 0;
        for (CoupEntity coup : manche.getCoupEntities()) {
            if (coup instanceof PoseTuileEntity) {
                nombre++;
            }
        }
        return nombre;
    }

    private boolean aPasse(MancheEntity manche, JoueurEntity joueur) {
        for (CoupEntity coup : manche.getCoupEntities()) {
            if (coup instanceof PasseEntity && coup.getMembreEntity() != null && coup.getMembreEntity().getId().equals(joueur.getId())) {
                return true;
            }
        }
        return false;
    }

    private JoueurEntity joueurSuivant(PartieEntity partie, MancheEntity manche, JoueurEntity joueur) {
        List<JoueurEntity> ordre = new ArrayList<>(partie.getJoueurs());
        ordre.sort(Comparator.comparing(JoueurEntity::getId));
        int position = 0;
        for (int i = 0; i < ordre.size(); i++) {
            if (ordre.get(i).getId().equals(joueur.getId())) {
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
}
