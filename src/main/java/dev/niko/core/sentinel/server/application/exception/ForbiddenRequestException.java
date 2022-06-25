package dev.niko.core.sentinel.server.application.exception;

public class ForbiddenRequestException extends RuntimeException {

    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
    
}
