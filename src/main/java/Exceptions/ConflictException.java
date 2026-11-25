package Exceptions;
//409
public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
