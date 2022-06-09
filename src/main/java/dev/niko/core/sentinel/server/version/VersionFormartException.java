package dev.niko.core.sentinel.server.version;

import dev.niko.core.sentinel.server.exception.BadRequestException;

public class VersionFormartException extends BadRequestException {

    public VersionFormartException(String message) {
        super(message);
    }
}
