package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import java.util.Optional;

import dev.niko.core.sentinel.server.app.domain.App;

public interface AppMapper {

    Optional<App> find(String uid) throws DataMapperException;

    void insert(App app) throws DataMapperException;

    void update(App app) throws DataMapperException;

    void delete(App app) throws DataMapperException;

}
