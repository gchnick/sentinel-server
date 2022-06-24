package dev.niko.core.sentinel.server.app.application.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String detail) {
        super(detail);
    }
}
