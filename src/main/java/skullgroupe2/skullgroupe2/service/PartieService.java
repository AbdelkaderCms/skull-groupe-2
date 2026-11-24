package skullgroupe2.skullgroupe2.service;

import org.springframework.stereotype.Service;

import skullgroupe2.skullgroupe2.entity.Partie;
import skullgroupe2.skullgroupe2.repository.PartieRepository;
import java.time.LocalDate;
import java.util.List;
import skullgroupe2.skullgroupe2.exception.RessourceIntrouvableException;


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

    // Retrouve une partie par son id
    public Partie trouverPartie(Long id){
        return partieRepository.findById(id)
                        .orElseThrow(() -> new RessourceIntrouvableException("Partie introuvable") ) ;
    }

    
    // Retourne l'historique : toutes les parties, de la plus récente à la plus ancienne
    public List<Partie> historique() {
        return partieRepository.findAllByOrderByDateDescIdDesc();
    }

    // Supprime une partie, ou lance une erreur 404 si elle n'existe pas
    public void supprimerPartie(Long id) {
        Partie partie = trouverPartie(id); // lance l'erreur si introuvable
        partieRepository.delete(partie);
    }
}
