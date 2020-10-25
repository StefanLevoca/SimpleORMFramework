package sk.jaro.simpleormframework.vynimky;

public class MissingIdException extends RuntimeException {
    public MissingIdException(String message) {
        super(message);
    }
}
