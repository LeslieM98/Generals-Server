package me.leslie.generals.exception.data;

public class CreationFailedException extends DataException {
    public CreationFailedException(String message) {
        super(message);
    }

    public CreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
