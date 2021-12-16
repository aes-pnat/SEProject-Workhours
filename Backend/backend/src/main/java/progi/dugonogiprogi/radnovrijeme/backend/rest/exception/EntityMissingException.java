package progi.dugonogiprogi.radnovrijeme.backend.rest.exception;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;

/**
 * Exception thrown if an entity is missing.
 */
public class EntityMissingException extends RuntimeException {
    public EntityMissingException() {
    }

    public EntityMissingException(String message) {
        super(message);
    }

    public EntityMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityMissingException(Throwable cause) {
        super(cause);
    }
}
