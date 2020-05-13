package cz.mazl.tul.blogic.exception;

public class CityAlreadyExistException extends RuntimeException {
    public CityAlreadyExistException(String message) {
        super(message);
    }
}
