package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.update.UpdateMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppMapperImpl implements AppMapper {

    private final UpdateMapper mapper;
    
    @Override
    public App toDomain(AppMap map) throws DataMapperException {
        return new App(
            map.getId(),
            map.getName(),
            map.getCurrentVersion(),
            map.getUpdateURL(),
            map.getUpdates().stream().map(u -> mapper.toDomain(u) ).toList(),
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
            domain.getUpdates().stream().map( d -> mapper.toMap(d) ).toList(),
            domain.getUid().toString()
        );
    }
    
}
