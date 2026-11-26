package Repositories;

import Entities.CoupEntity;
import Entities.EnchereEntity;
import Entities.JoueurEntity;
import Entities.MembreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnchereRepository extends JpaRepository<EnchereEntity,Integer> {

    @Query("select c from MancheEntity m join m.coupEntities c where m.id = :mancheId and type(c) = EnchereEntity order by c.id desc")
    List<CoupEntity> findEncheresDeLaManche(@Param("mancheId") Integer mancheId);

    @Query("select count(c) from MancheEntity m join m.coupEntities c where m.id = :mancheId and type(c) = PoseTuileEntity")
    long countTuilesPosees(@Param("mancheId") Integer mancheId);

    @Query("select count(c) from MancheEntity m join m.coupEntities c where m.id = :mancheId and type(c) = PasseEntity and c.membreEntity.id = :membreId")
    long countPasses(@Param("mancheId") Integer mancheId, @Param("membreId") Long membreId);

    @Query("select c.membreEntity from CoupEntity c where c.id = :coupId")
    MembreEntity findMembreDuCoup(@Param("coupId") Integer coupId);

    @Query("select m.joueurEntity from MancheEntity m where m.id = :mancheId")
    JoueurEntity findChallenger(@Param("mancheId") Integer mancheId);

    @Query("select coalesce(max(c.id), 0) from CoupEntity c")
    Integer findDernierIdCoup();

    @Modifying(flushAutomatically = true)
    @Query("update CoupEntity c set c.membreEntity = :membre where c.id = :coupId")
    void affecterMembre(@Param("coupId") Integer coupId, @Param("membre") MembreEntity membre);

    @Modifying(flushAutomatically = true)
    @Query(value = "update coup_entity set manche_id = :mancheId where id = :coupId", nativeQuery = true)
    void ajouterALaManche(@Param("coupId") Integer coupId, @Param("mancheId") Integer mancheId);

    @Modifying(flushAutomatically = true)
    @Query("update MancheEntity m set m.joueurEntity = :challenger where m.id = :mancheId")
    void definirChallenger(@Param("mancheId") Integer mancheId, @Param("challenger") JoueurEntity challenger);
}
