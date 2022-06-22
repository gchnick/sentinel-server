package dev.niko.core.sentinel.server.app.domain.version;

import dev.niko.core.sentinel.server.app.domain.exception.BadRequestException;

public class VersionFormartException extends BadRequestException {

    public VersionFormartException(String message) {
        super(message);
    }
}
