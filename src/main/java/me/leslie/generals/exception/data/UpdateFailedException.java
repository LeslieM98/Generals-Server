package me.leslie.generals.exception.data;

public class UpdateFailedException extends DataException {
    public UpdateFailedException(String message) {
        super(message);
    }

    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
