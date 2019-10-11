package me.leslie.generals.exception.data;

public class DeletionFailedException extends DataException {
    public DeletionFailedException(String message) {
        super(message);
    }

    public DeletionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
