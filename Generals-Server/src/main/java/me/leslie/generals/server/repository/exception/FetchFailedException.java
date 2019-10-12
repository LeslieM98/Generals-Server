package me.leslie.generals.server.repository.exception;

public class FetchFailedException extends DataException {
    public FetchFailedException(String message) {
        super(message);
    }

    public FetchFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
