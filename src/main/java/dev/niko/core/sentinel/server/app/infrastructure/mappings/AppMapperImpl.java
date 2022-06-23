package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import dev.niko.core.sentinel.server.app.domain.App;

public class AppMapperImpl implements AppMapper {
    
    @Override
    public App toDomain(AppMap map) throws DataMapperException {
        return new App(
            map.getId(),
            map.getName(),
            map.getCurrentVersion(),
            map.getUpdateURL(),
            null,
            map.getUid()
        );
    }

    @Override
    public AppMap toMap(App domain) throws DataMapperException {
        return new AppMap(
            domain.getId(),
            domain.getName(),
            domain.getCurrentVersion().value(),
            domain.getUpdateURL(),
            null,
            domain.getUid().toString()
        );
    }
    
}
