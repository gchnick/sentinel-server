package dev.niko.core.sentinel.server.app.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}