package dev.niko.core.sentinel.server.shared.mapper;

import dev.niko.core.sentinel.server.domain.exception.ConflictException;

public class DataMapperException extends ConflictException {

    public DataMapperException(String message) {
        super(message);
    }
    
}
