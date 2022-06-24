package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

public interface AppService {

    UUID create(String name);

    App get(UUID uid);

    void setName(UUID uid, String name);

    void releaseUpdate(App app, Update update);

    boolean isCurrent(UUID uid, String version);

    void delete(UUID uid);
}
