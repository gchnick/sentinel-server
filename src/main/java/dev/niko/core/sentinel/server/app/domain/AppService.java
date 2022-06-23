package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

import dev.niko.core.sentinel.server.app.domain.update.UpdateDTO;

public interface AppService {

    UUID create(String name);

    App get(UUID uid);

    void setName(UUID uid, String name);

    UUID releaseUpdate(App app, UpdateDTO release);

    boolean isCurrent(UUID uid, String version);

    void delete(UUID uid);
}
