package dev.niko.core.sentinel.server.app.shared.mapper.update;

import dev.niko.core.sentinel.server.app.application.UpdateRequest;
import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.UpdateMap;
import dev.niko.core.sentinel.server.app.shared.mapper.Mapper;

public interface UpdateMapper extends Mapper<Update, UpdateMap> {

    Update toDomain(UpdateRequest request);
    
}
