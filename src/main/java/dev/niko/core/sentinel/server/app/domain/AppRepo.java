package dev.niko.core.sentinel.server.app.domain;

import java.util.Optional;
import java.util.UUID;

public interface AppRepo {

    Optional<App> findByUid(UUID uid);

    boolean isAlreadyRegisteredName(String name);

    UUID save(App app);

    void delete(App app);
}
