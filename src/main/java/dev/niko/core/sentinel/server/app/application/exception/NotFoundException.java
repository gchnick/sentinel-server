package dev.niko.core.sentinel.server.app.application.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String detail) {
        super(detail);
    }  
}
