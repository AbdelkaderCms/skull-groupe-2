package Services;

import DTOs.BannirMembreDTO;
import DTOs.SignalerJoueurDTO;
import Entities.MembreEntity;
import Entities.SignalementEntity;
import Exceptions.InvalidPayloadException;
import Exceptions.NotFoundException;
import Repositories.MembreRepository;
import Repositories.SignalementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service des joueurs : signalements et bannissement
@Service
public class PlayerService {

    private final SignalementRepository signalementRepository;
    private final MembreRepository membreRepository;

    public PlayerService(SignalementRepository signalementRepository, MembreRepository membreRepository) {
        this.signalementRepository = signalementRepository;
        this.membreRepository = membreRepository;
    }

    // Enregistre un signalement : 400 si les champs sont invalides, 404 si le joueur signalé n'existe pas
    public SignalementEntity signalerJoueur(SignalerJoueurDTO dto) {
        if (dto == null || dto.getIdJoueurSignale() == null
                || dto.getMotif() == null || dto.getMotif().isBlank()) {
            throw new InvalidPayloadException("Champs invalides");
        }
        MembreEntity membre = membreRepository.findById(dto.getIdJoueurSignale())
                .orElseThrow(() -> new NotFoundException("Joueur signalé introuvable"));
        SignalementEntity signalement = new SignalementEntity();
        signalement.setMotif(dto.getMotif());
        signalement.setDescription(dto.getDescription());
        signalement.setMembreEntity(membre);
        return signalementRepository.save(signalement);
    }

    // Retourne tous les signalements enregistrés (pour l'administrateur)
    public List<SignalementEntity> consulterSignalements() {
        return signalementRepository.findAll();
    }

    // Bannit un membre : 400 si la demande est invalide, 404 si le membre n'existe pas
    public void bannirMembre(Long idMembre, BannirMembreDTO dto) {
        if (dto == null || !Boolean.TRUE.equals(dto.getEstBanni())) {
            throw new InvalidPayloadException("Requete invalide");
        }
        MembreEntity membre = membreRepository.findById(idMembre)
                .orElseThrow(() -> new NotFoundException("Membre inexistant"));
        membre.setEstBanni(true);
        membreRepository.save(membre);
    }
}