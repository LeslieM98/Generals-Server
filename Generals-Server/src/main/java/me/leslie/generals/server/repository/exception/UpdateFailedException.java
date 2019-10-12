package me.leslie.generals.server.repository.exception;


public class UpdateFailedException extends DataException {
    public UpdateFailedException(String message) {
        super(message);
    }

    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
