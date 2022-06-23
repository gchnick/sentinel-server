package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

import dev.niko.core.sentinel.server.app.domain.update.ReleaseDTO;

public interface AppService {

    UUID create(String name);

    App get(UUID uid);

    void setName(UUID uid, String name);

    UUID releaseUpdate(App app, ReleaseDTO release);

    boolean isCurrent(UUID uid, String version);

    void delete(UUID uid);
}
