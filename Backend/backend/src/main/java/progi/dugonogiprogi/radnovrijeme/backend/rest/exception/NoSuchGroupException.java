package progi.dugonogiprogi.radnovrijeme.backend.rest.exception;

public class NoSuchGroupException extends RuntimeException {
    public NoSuchGroupException() {
    }

    public NoSuchGroupException(String message) {
        super(message);
    }

    public NoSuchGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGroupException(Throwable cause) {
        super(cause);
    }
}
