package dev.niko.core.sentinel.server.app;

import java.util.Collection;
import java.util.UUID;

import dev.niko.core.sentinel.server.app.release.Release;
import dev.niko.core.sentinel.server.app.release.ReleaseDTO;

public interface AppService {

    App create(String name);

    App get(UUID uid);

    Collection<App> get();

    void setName(UUID uid, String name);

    Release dumpVersion(App app, ReleaseDTO release);

    boolean isCurrent(UUID uid, String version);

    void delete(UUID uid);
}
