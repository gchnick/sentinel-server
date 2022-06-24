package dev.niko.core.sentinel.server.app.domain.exception;

public class VersionUpdateIsLessException extends BadRequestException {

    public VersionUpdateIsLessException(String message) {
        super(message);
    }
}
