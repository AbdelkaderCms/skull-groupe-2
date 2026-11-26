package Repositories;

import Entities.PartieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartieRepository extends JpaRepository<PartieEntity,Long> {

    // Les 5 dernières parties d'un joueur, de la plus récente à la plus ancienne (diagramme : trouverHistorique)
    List<PartieEntity> findTop5ByJoueurs_IdOrderByDateDesc(Long idMembre);
}
