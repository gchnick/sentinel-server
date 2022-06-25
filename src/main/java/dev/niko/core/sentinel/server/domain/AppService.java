package dev.niko.core.sentinel.server.domain;

import java.util.UUID;

public interface AppService {

    UUID create(App app);

    App get(UUID uid);

    void setName(UUID uid, String name);

    void releaseUpdate(App app, Update update);

    boolean isCurrent(UUID uid, String version);

    void delete(UUID uid);
}
