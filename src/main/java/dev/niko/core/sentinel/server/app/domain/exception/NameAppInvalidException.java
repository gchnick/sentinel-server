package dev.niko.core.sentinel.server.app.domain.exception;

public class NameAppInvalidException extends RuntimeException {

    public NameAppInvalidException(String message) {
        super(message);
    }
}
