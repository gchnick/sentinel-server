package dev.niko.core.sentinel.server.domain.exception;

public class UpdateNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Update details not found";

    public UpdateNotFoundException() {
        super(MESSAGE);
    }
}