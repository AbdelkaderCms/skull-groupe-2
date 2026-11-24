package skullgroupe2.skullgroupe2.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Gestionnaire d'erreurs : attrape les exceptions de tous les controllers et choisit le code à renvoyer
@RestControllerAdvice
public class GestionnaireErreurs {

    // Objet introuvable : code 404
    @ExceptionHandler(RessourceIntrouvableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> introuvable(RessourceIntrouvableException e) {
        return Map.of("erreur", e.getMessage());
    }

    // Règle du jeu non respectée ou demande invalide : code 400
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> requeteInvalide(IllegalArgumentException e) {
        return Map.of("erreur", e.getMessage());
    }
}