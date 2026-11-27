package Repositories;

import Entities.EvenementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<EvenementEntity,Integer> {
}
