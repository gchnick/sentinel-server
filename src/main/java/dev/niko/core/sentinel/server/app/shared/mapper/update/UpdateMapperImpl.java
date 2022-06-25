package dev.niko.core.sentinel.server.app.shared.mapper.update;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.app.application.request.UpdateRequest;
import dev.niko.core.sentinel.server.app.domain.Update;
import dev.niko.core.sentinel.server.app.infrastructure.app.mapping.UpdateMap;
import dev.niko.core.sentinel.server.app.shared.mapper.DataMapperException;

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
        String uid = null;
        if(domain.getUid() != null) {
            uid = domain.getUid().toString();
        }
        return new UpdateMap(
            domain.getId(),
            domain.getVersion().value(),
            domain.getOverview(),
            uid
        );
    }

    @Override
    public Update toDomain(UpdateRequest request) {
        return new Update(
            request.version(),
            request.overview()
        );
    }
    
}
