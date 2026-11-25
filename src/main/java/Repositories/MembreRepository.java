package Repositories;

import Entities.MembreEntity;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends JpaRepository<MembreEntity,Long> {
    boolean existsByPseudo(String pseudo);
    MembreEntity findByPseudo(String pseudo);
}
