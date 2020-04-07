package cz.mazl.tul.blogic.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
