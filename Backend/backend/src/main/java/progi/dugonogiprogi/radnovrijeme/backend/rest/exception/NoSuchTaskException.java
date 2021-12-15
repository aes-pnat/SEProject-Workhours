package progi.dugonogiprogi.radnovrijeme.backend.rest.exception;

public class NoSuchTaskException extends RuntimeException {

    public NoSuchTaskException() {
        super();
    }

    public NoSuchTaskException(String msg) {
        super("Task with the name \"" + msg + "\" doesn't exist.");
    }

}
