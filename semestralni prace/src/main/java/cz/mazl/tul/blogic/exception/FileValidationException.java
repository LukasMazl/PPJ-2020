package cz.mazl.tul.blogic.exception;

public class FileValidationException extends RuntimeException {
    public FileValidationException(String errorMessage) {
        super(errorMessage);
    }
}
