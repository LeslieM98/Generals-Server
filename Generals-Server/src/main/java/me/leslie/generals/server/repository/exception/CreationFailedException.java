package me.leslie.generals.server.repository.exception;

public class CreationFailedException extends DataException {
    public CreationFailedException(String message) {
        super(message);
    }

    public CreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
