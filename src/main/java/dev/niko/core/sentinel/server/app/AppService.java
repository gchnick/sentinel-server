package dev.niko.core.sentinel.server.app;

import dev.niko.core.sentinel.server.app.release.Release;
import dev.niko.core.sentinel.server.app.release.ReleaseDTO;

public interface AppService {

    App create(String name);

    App get(String uid);

    void setName(String uid, String name);

    Release dumpVersion(String uid, ReleaseDTO release);

    boolean isCurrent(String uid, String version);

    void delete(String uid);
}
