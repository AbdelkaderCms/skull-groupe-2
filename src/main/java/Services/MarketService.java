package Services;
import DTOs.SetMarketplaceDTO;
import Entities.MembreEntity;
import Entities.SetEntity;
import Exceptions.ConflictException;
import Exceptions.InvalidPayloadException;
import Exceptions.NotFoundException;
import Exceptions.UnauthorizedException;
import Models.SetMarketplaceData;
import Repositories.MembreRepository;
import Repositories.SetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MarketService {
    private final MembreRepository membreRepository;
    private final SetRepository setRepository;
    public MarketService(MembreRepository membreRepository, SetRepository setRepository){
        this.membreRepository=membreRepository;
        this.setRepository=setRepository;
    }
    @Transactional
    public List<SetMarketplaceData> consulterMarketplace(String pseudo) throws UnauthorizedException {
        if (this.membreRepository.existsByPseudo(pseudo) == false) {
            throw new UnauthorizedException("Joueur inexistant");
        }
        MembreEntity membre = this.membreRepository.findByPseudo(pseudo);
        List<SetEntity> sets = setRepository.findAllSets();
        List<SetMarketplaceData> resultat = new ArrayList<>();
        SetEntity setCourant = membre.getSetCourant();
        Set<SetEntity> setsPossedes = membre.getSetsPossedes();

        for (SetEntity set : sets) {
            SetMarketplaceData setMarketplaceData = new SetMarketplaceData(
                    set,
                    setsPossedes.stream().anyMatch(s -> s.getId().equals(set.getId())),
                    set.getId().equals(setCourant.getId())
            );
            resultat.add(setMarketplaceData);
        }

        return resultat;
    }

    @Transactional
    public void acheterAspect(Integer aspectId, String pseudo) throws ConflictException, UnauthorizedException, NotFoundException {
        if (this.membreRepository.existsByPseudo(pseudo) == false) {
            throw new UnauthorizedException("Joueur inexistant");
        }
        MembreEntity membre = this.membreRepository.findByPseudo(pseudo);
        SetEntity set = setRepository.findById(aspectId)
                .orElseThrow(() -> new NotFoundException("Set inexistant"));
        if(membre.getSetsPossedes().stream().anyMatch(s -> s.getId().equals(set.getId()))){
            throw new ConflictException("Achat impossible:set déjà possédé");
        }
        if(set.getPrix()>membre.getMonnaie()){
            throw new ConflictException("Achat impossible:monnaie manquante");
        }

        membre.setMonnaie(membre.getMonnaie()-set.getPrix());
        membre.addSetPossedes(set);
    }

    @Transactional
    public void definirAspectCourant(Integer aspectId, String pseudo) throws ConflictException, UnauthorizedException, NotFoundException{
        if (this.membreRepository.existsByPseudo(pseudo) == false) {
            throw new UnauthorizedException("Joueur inexistant");
        }
        MembreEntity membre = this.membreRepository.findByPseudo(pseudo);
        SetEntity set = setRepository.findById(aspectId)
                .orElseThrow(() -> new NotFoundException("Set inexistant"));
        if(membre.getSetsPossedes().stream().anyMatch(s -> s.getId().equals(set.getId()))){
            throw new ConflictException("Set non possédé");
        }
        membre.setSetCourant(set);
    }
}

