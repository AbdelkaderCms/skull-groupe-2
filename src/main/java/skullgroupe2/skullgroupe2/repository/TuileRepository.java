package skullgroupe2.skullgroupe2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skullgroupe2.skullgroupe2.entity.Tuile;

// Repository de Tuile : permet d'enregistrer et de retrouver les tuiles dans la base

public interface TuileRepository extends JpaRepository<Tuile, Long> {
}
