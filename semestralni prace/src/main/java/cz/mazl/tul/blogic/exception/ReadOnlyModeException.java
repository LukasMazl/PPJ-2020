package cz.mazl.tul.blogic.exception;

public class ReadOnlyModeException extends RuntimeException {
    public ReadOnlyModeException(String message) {
        super(message);
    }
}
