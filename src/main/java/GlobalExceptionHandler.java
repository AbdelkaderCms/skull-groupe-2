import Exceptions.InvalidPayloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //404
    public ProblemDetail handleInvalidPayload(InvalidPayloadException exception){
        ProblemDetail problem= ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        problem.setTitle("Champ manquant ou invalide");
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }
}
