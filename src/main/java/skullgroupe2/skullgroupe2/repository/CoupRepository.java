package skullgroupe2.skullgroupe2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skullgroupe2.skullgroupe2.entity.Coup;

// Repository de Coup : permet d'enregistrer et de retrouver les coups dans la base
public interface CoupRepository extends JpaRepository<Coup, Long> {
}
