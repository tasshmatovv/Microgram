package kg.attractor.microgram.exceptions;

public class NickAlreadyExistsException extends RuntimeException {
    public NickAlreadyExistsException(String message) {
        super(message);
    }
}
