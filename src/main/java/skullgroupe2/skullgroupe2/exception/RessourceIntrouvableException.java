package skullgroupe2.skullgroupe2.exception;

// Exception lancé quand un objet demandé n'existe pas (partie, tuile, coup...)
public class RessourceIntrouvableException extends RuntimeException {

    public RessourceIntrouvableException(String message) {
        super(message); 
    }
}