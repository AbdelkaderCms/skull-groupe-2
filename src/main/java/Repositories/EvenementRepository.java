package Repositories;

import Entities.CoupEntity;
import Entities.EvenementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementRepository extends JpaRepository<EvenementEntity,Integer> {

    @Query("select c from PartieEntity p join p.mancheEntities m join m.coupEntities c where p.id = :partieId order by c.id desc")
    List<CoupEntity> findCoupsDeLaPartie(@Param("partieId") Long partieId);
}
