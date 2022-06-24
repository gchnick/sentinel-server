package dev.niko.core.sentinel.server.app.domain.exception;

public class VersionFormartException extends BadRequestException {

    public VersionFormartException(String message) {
        super(message);
    }
}
