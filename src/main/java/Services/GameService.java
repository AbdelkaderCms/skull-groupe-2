package Services;

import DTOs.CreerPartieDTO;
import Entities.JoueurEntity;
import Entities.PartieEntity;
import Exceptions.InvalidPayloadException;
import Repositories.JoueurRepository;
import Repositories.PartieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final List<String> iaListe = new ArrayList<>(List.of("AliceIA", "BobIA", "CharlyIA", "DavidIA", "EmmaIA"));
    private  final JoueurRepository joueurRepository;
    private final PartieRepository partieRepository;
    public GameService(JoueurRepository joueurRepository, PartieRepository partieRepository) {
        this.joueurRepository = joueurRepository;
        this.partieRepository = partieRepository;
    }
    @Transactional
    public PartieEntity creePartie(CreerPartieDTO dto, String pseudo) throws InvalidPayloadException {
        if(dto.getNbJoueurs()>6 || dto.getNbJoueurs()<3){
            throw new InvalidPayloadException("Nombre de joueurs invalide");
        }
        if(dto.getNbIA()>(dto.getNbJoueurs()-1)|| dto.getNbIA()<0){
            throw new InvalidPayloadException("Nombre d'IA invalide");
        }
        PartieEntity partieEntity=new PartieEntity(dto.getNbJoueurs(),dto.getNbIA());
        for(int i=0;i<dto.getNbIA();i++){
            partieEntity.ajoutJoueur(this.joueurRepository.getJoueurEntitiesByPseudo(this.iaListe.get(i)));
        }
        partieEntity.ajoutJoueur(this.joueurRepository.getJoueurEntitiesByPseudo(pseudo));
        this.partieRepository.save(partieEntity);
        return partieEntity;
    }

}
