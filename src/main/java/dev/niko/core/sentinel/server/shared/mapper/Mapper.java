package dev.niko.core.sentinel.server.shared.mapper;

/**
 * T is Domain class
 * V is Map class
 */
public interface Mapper<T, V> {
    
    T toDomain(V map) throws DataMapperException;

    V toMap(T domain) throws DataMapperException;

}
