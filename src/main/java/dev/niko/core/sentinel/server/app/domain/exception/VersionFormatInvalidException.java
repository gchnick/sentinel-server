package dev.niko.core.sentinel.server.app.domain.exception;

public class VersionFormatInvalidException extends BadRequestException {

    public VersionFormatInvalidException(String message) {
        super(message);
    }
}
