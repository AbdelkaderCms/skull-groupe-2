package Repositories;

import Entities.SignalementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository des signalements : enregistrer et retrouver les signalements
@Repository
public interface SignalementRepository extends JpaRepository<SignalementEntity, Integer> {
}