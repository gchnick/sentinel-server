package dev.niko.core.sentinel.server.app.shared.mapper;

import dev.niko.core.sentinel.server.app.domain.exception.ConflictException;

public class DataMapperException extends ConflictException {

    public DataMapperException(String message) {
        super(message);
    }
    
}
