package progi.dugonogiprogi.radnovrijeme.backend.rest.exception;

public class RequiredDataException extends RuntimeException {
    public RequiredDataException() {
    }

    public RequiredDataException(String message) {
        super(message);
    }

    public RequiredDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredDataException(Throwable cause) {
        super(cause);
    }
}
