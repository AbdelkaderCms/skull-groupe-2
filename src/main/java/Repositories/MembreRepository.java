package Repositories;

import Entities.MembreEntity;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MembreRepository extends JpaRepository<MembreEntity,Long> {
    boolean existsByPseudo(String pseudo);
    MembreEntity findByPseudo(String pseudo);


    // Tous les membres, du plus de points au moins de points (diagramme : trouverTousLesMembresTriesParPoints)
    List<MembreEntity> findAllByOrderByPointsDesc();
}
