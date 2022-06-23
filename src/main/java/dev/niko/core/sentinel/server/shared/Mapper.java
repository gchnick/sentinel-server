package dev.niko.core.sentinel.server.shared;

import dev.niko.core.sentinel.server.app.infrastructure.mappings.DataMapperException;

/**
 * T is Domain class
 * V is Map class
 */
public interface Mapper<T, V> {
    
    T toDomain(V map) throws DataMapperException;

    V toMap(T domain) throws DataMapperException;

}
