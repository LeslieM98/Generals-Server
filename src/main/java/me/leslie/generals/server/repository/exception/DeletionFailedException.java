package me.leslie.generals.server.repository.exception;

public class DeletionFailedException extends DataException {
    public DeletionFailedException(String message) {
        super(message);
    }

    public DeletionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
