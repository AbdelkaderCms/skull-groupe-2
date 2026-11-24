package Repositories;

import Entities.JoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<JoueurEntity,Long> {
    public JoueurEntity getJoueurEntitiesByPseudo(String pseudo);
}
