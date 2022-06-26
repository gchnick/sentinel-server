package dev.niko.core.sentinel.server.domain.exception;

public class AppNameInvalidException extends BadRequestException {

    private static final String MESSAGE = "Application name is invalid";

    public AppNameInvalidException() {
        super(MESSAGE);
    }
}
