package dev.niko.core.sentinel.server.app.domain.exception;

public class NameAppInvalidException extends BadRequestException {

    public NameAppInvalidException(String message) {
        super(message);
    }
}
