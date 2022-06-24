package dev.niko.core.sentinel.server.app.application.exception;

public class UnauthorizedException extends RuntimeException{

    private static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
