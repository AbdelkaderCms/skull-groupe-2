package skullgroupe2.skullgroupe2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skullgroupe2.skullgroupe2.entity.Partie;

// Repository de Partie : permet d'enregistrer et de retrouver les parties dans la base
public interface PartieRepository extends JpaRepository<Partie, Long> {
}
