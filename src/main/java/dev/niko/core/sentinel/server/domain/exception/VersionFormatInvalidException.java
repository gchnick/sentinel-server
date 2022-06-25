package dev.niko.core.sentinel.server.domain.exception;

public class VersionFormatInvalidException extends BadRequestException {

    public VersionFormatInvalidException(String message) {
        super(message);
    }
}
