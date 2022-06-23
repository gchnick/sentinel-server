package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import dev.niko.core.sentinel.server.app.domain.update.Update;

public class UpdateMemento implements Memento {

    private Update entity;
    private UpdateMap map;

    public UpdateMemento(Update entity, UpdateMap map) {
        this.entity = entity;
        this.map= map;

        if(entity == null) {
            this.entity = new Update(
                map.getId(),
                map.getVersion(),
                map.getOverview(),
                map.getUid()
            );
        }
    }

    public static UpdateMap getMapping(Update entity) {
        return new UpdateMap(
            entity.getId(),
            entity.getVersion().value(),
            entity.getOverview(),
            entity.getUid(),
            entity
        );
    }

    @Override
    public void update() {
        map.setId(entity.getId());
        map.setVersion(entity.getVersion().value());
        map.setOverview(entity.getOverview());
        map.setUid(entity.getUid());
    }
}
