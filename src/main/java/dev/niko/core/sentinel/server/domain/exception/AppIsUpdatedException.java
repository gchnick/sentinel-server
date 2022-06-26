package dev.niko.core.sentinel.server.domain.exception;

public class AppIsUpdatedException extends SuccessException {

    private static final String MESSAGE = "Application is updated";

    public AppIsUpdatedException() {
        super(MESSAGE);
    }
}