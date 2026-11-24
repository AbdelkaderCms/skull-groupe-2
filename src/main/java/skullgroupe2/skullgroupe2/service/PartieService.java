package skullgroupe2.skullgroupe2.service;

import org.springframework.stereotype.Service;

import skullgroupe2.skullgroupe2.entity.Partie;
import skullgroupe2.skullgroupe2.repository.PartieRepository;
import java.time.LocalDate;
import java.util.List;


// Service de Partie : contient les règles du jeu liées aux parties
@Service 
public class PartieService {

    private final PartieRepository partieRepository;

    public PartieService(PartieRepository partieRepository){
        this.partieRepository = partieRepository;
    }
    

    // Crée une partie si le nombre total de joueurs (humains + IA) est entre 3 et 6
    public Partie creerPartie(Integer nbJoueurs, Integer nbIA){
        if (nbJoueurs == null || nbIA == null) {
            throw new IllegalArgumentException("nbJoueurs et nbIA sont obligatoires");
        }
        if(nbJoueurs + nbIA < 3 || nbJoueurs + nbIA > 6){
            throw new IllegalArgumentException("la somme des joueurs doit etre entre 3 et 6");
        }
        Partie partie = new Partie();
        partie.setNbJoueurs(nbJoueurs);
        partie.setNbIA(nbIA);
        partie.setDate(LocalDate.now()); // la date d'aujourd'hui
        return partieRepository.save(partie);
    }



    // Retourne toutes les parties enregistrées
    public List<Partie> listerParties(){
        return partieRepository.findAll();
    }
}
