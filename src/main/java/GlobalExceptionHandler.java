import Exceptions.ConflictException;
import Exceptions.ForbiddenException;
import Exceptions.InvalidPayloadException;
import Exceptions.NotFoundException;
import Exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Construit la réponse d'erreur : statut, titre, détail, date, et un champ "message" (format du contrat API)
    private ProblemDetail construire(HttpStatus statut, String titre, String message) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(statut, message);
        problem.setTitle(titre);
        problem.setProperty("message", message);
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    //400
    @ExceptionHandler(InvalidPayloadException.class)
    public ProblemDetail handleInvalidPayload(InvalidPayloadException exception) {
        return construire(HttpStatus.BAD_REQUEST, "Champ manquant ou invalide", exception.getMessage());
    }

    //401
    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorized(UnauthorizedException exception) {
        return construire(HttpStatus.UNAUTHORIZED, "Non authentifié", exception.getMessage());
    }

    //403
    @ExceptionHandler(ForbiddenException.class)
    public ProblemDetail handleForbidden(ForbiddenException exception) {
        return construire(HttpStatus.FORBIDDEN, "Accès interdit", exception.getMessage());
    }

    //404
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException exception) {
        return construire(HttpStatus.NOT_FOUND, "Ressource introuvable", exception.getMessage());
    }

    //409
    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflict(ConflictException exception) {
        return construire(HttpStatus.CONFLICT, "Conflit", exception.getMessage());
    }
}