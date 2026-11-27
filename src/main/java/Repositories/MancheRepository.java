package Repositories;

import Entities.MancheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MancheRepository extends JpaRepository<MancheEntity,Integer> {
}
