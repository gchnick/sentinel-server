package dev.niko.core.sentinel.server.shared.mapper.update;

import dev.niko.core.sentinel.server.application.app.request.UpdateRequest;
import dev.niko.core.sentinel.server.domain.Update;
import dev.niko.core.sentinel.server.infrastructure.app.mapping.UpdateMap;
import dev.niko.core.sentinel.server.shared.mapper.Mapper;

public interface UpdateMapper extends Mapper<Update, UpdateMap> {

    Update toDomain(UpdateRequest request);
    
}
