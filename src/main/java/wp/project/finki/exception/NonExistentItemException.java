package wp.project.finki.exception;


public class NonExistentItemException extends RuntimeException {
    public NonExistentItemException(String message) {
        super(message);
    }
}
