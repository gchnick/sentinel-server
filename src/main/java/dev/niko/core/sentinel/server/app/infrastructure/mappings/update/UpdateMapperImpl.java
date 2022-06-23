package dev.niko.core.sentinel.server.app.infrastructure.mappings.update;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.DataMapperException;

@Component
public class UpdateMapperImpl implements UpdateMapper {

    @Override
    public Update toDomain(UpdateMap map) throws DataMapperException {
        return new Update(
            map.getId(),
            map.getVersion(),
            map.getOverview(),
            map.getUid()
        );
    }

    @Override
    public UpdateMap toMap(Update domain) throws DataMapperException {
        return new UpdateMap(
            domain.getId(),
            domain.getVersion().value(),
            domain.getOverview(),
            domain.getUid()
        );
    }
    
}
