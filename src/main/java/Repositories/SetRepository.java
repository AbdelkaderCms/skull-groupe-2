package Repositories;

import Entities.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<SetEntity,Integer> {
    List<SetEntity> findAllSets();
}
