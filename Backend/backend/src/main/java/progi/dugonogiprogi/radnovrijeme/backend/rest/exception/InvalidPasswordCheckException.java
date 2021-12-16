package progi.dugonogiprogi.radnovrijeme.backend.rest.exception;

public class InvalidPasswordCheckException extends RuntimeException{
    public InvalidPasswordCheckException() {
    }

    public InvalidPasswordCheckException(String message) {
        super(message);
    }

    public InvalidPasswordCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordCheckException(Throwable cause) {
        super(cause);
    }
}
