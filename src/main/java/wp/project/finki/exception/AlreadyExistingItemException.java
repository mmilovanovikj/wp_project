package wp.project.finki.exception;


public class AlreadyExistingItemException extends RuntimeException {
    public AlreadyExistingItemException(String message) {
        super(message);
    }
}
