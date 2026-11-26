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
        return enchereCourante(manche.getId());
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

        if (participe(partie, membre) == false) {
            throw new ForbiddenException("Vous ne participez pas à cette partie");
        }
        if (Boolean.TRUE.equals(manche.getEstEnCours()) == false) {
            throw new ConflictException("Enchère impossible : la manche n'est pas en cours");
        }
        if (this.enchereRepository.findChallenger(mancheId) != null) {
            throw new ConflictException("Enchère impossible : les enchères sont terminées");
        }
        if (this.enchereRepository.countPasses(mancheId, membre.getId()) > 0) {
            throw new ConflictException("Enchère impossible : vous vous êtes retiré de la manche");
        }
        int minimum = enchereCourante(mancheId).getEnchereMinimale();
        int totalTuiles = (int) this.enchereRepository.countTuilesPosees(mancheId);
        int valeur = dto.getValeur();
        if (valeur < minimum || valeur > totalTuiles) {
            throw new ConflictException("Enchère impossible : la valeur doit être comprise entre " + minimum + " et " + totalTuiles);
        }

        EnchereEntity enchere = new EnchereEntity(valeur);
        enchere.setId(this.enchereRepository.findDernierIdCoup() + 1);
        this.enchereRepository.save(enchere);
        this.enchereRepository.affecterMembre(enchere.getId(), membre);
        this.enchereRepository.ajouterALaManche(enchere.getId(), mancheId);

        //Fin des enchères
        String challenger = null;
        if (valeur == totalTuiles || tousLesAutresSeSontRetires(partie, mancheId, membre)) {
            this.enchereRepository.definirChallenger(mancheId, membre);
            challenger = membre.getPseudo();
        }
        return new EtatMancheData(mancheId, valeur, membre.getPseudo(), challenger);
    }

    private EnchereCouranteData enchereCourante(Integer mancheId) {
        List<CoupEntity> encheres = this.enchereRepository.findEncheresDeLaManche(mancheId);
        if (encheres.isEmpty()) {
            return new EnchereCouranteData(null, null, 1);
        }
        EnchereEntity derniere = (EnchereEntity) encheres.get(0);
        MembreEntity encherisseur = this.enchereRepository.findMembreDuCoup(derniere.getId());
        String pseudo = null;
        if (encherisseur != null) {
            pseudo = encherisseur.getPseudo();
        }
        return new EnchereCouranteData(derniere.getValeur(), pseudo, derniere.getValeur() + 1);
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

    private boolean participe(PartieEntity partie, MembreEntity membre) {
        if (partie.getJoueurs() != null) {
            for (JoueurEntity joueur : partie.getJoueurs()) {
                if (Objects.equals(joueur.getId(), membre.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tousLesAutresSeSontRetires(PartieEntity partie, Integer mancheId, MembreEntity membre) {
        for (JoueurEntity joueur : partie.getJoueurs()) {
            if (Objects.equals(joueur.getId(), membre.getId()) == false
                    && this.enchereRepository.countPasses(mancheId, joueur.getId()) == 0) {
                return false;
            }
        }
        return true;
    }
}
