package skullgroupe2.skullgroupe2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skullgroupe2.skullgroupe2.entity.Partie;
import java.util.List;

// Repository de Partie : permet d'enregistrer et de retrouver les parties dans la base
public interface PartieRepository extends JpaRepository<Partie, Long> {

    // Méthode sur mesure : toutes les parties, de la plus récente à la plus ancienne
    List<Partie> findAllByOrderByDateDescIdDesc();
}
