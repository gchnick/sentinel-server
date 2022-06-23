package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import java.util.List;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.update.UpdateMap;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.update.UpdateMemento;

public class AppMemento implements Memento {

    private App entity;
    private AppMap map;

    public AppMemento(App entity, AppMap map) {
        
        this.map = map;
        
        if(entity == null) {
            this.entity = new App(
                map.getId(),
                map.getName(),
                map.getCurrentVersion(),
                map.getUpdateURL(),
                getUpdates(),
                map.getUid()
            );
        }
        
        this.entity = entity;
    }

    private List<Update> getUpdates() {
        return map.getUpdates().stream().map( u -> u.getEntity()).toList();
    }

    private static List<UpdateMap> getUpdates(List<Update> updates) {
        return updates.stream().map( UpdateMemento::getMapping ).toList();
    }

    @Override
    public void update() {
        map.setId(entity.getId());
        map.setName(entity.getName());
        map.setCurrentVersion(entity.getCurrentVersion().value());
        map.setUpdateURL(entity.getUpdateURL());
        map.setUpdates(getUpdates(entity.getUpdates()));
        map.setUid(entity.getUid());
    }
}