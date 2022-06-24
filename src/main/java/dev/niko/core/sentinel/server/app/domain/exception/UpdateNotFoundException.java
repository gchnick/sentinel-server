package dev.niko.core.sentinel.server.app.domain.exception;

public class UpdateNotFoundException extends NotFoundException {

    public UpdateNotFoundException(String message) {
        super(message);
    }
}