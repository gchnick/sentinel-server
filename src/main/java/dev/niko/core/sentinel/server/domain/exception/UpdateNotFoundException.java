package dev.niko.core.sentinel.server.domain.exception;

public class UpdateNotFoundException extends NotFoundException {

    public UpdateNotFoundException(String message) {
        super(message);
    }
}