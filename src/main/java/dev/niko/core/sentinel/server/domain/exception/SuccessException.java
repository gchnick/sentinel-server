package dev.niko.core.sentinel.server.domain.exception;

public class SuccessException extends RuntimeException {

    public SuccessException(String message) {
        super(message);
    }
}