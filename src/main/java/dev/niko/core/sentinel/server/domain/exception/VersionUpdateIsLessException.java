package dev.niko.core.sentinel.server.domain.exception;

public class VersionUpdateIsLessException extends BadRequestException {

    private static final String MESSAGE = "Version update is less that current version";

    public VersionUpdateIsLessException() {
        super(MESSAGE);
    }
}
