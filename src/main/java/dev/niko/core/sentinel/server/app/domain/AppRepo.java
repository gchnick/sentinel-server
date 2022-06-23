package dev.niko.core.sentinel.server.app.domain;

import java.util.Optional;
import java.util.UUID;

import dev.niko.core.sentinel.server.shared.Repository;

public interface AppRepo extends Repository<App> {

    Optional<App> findByUid(UUID uid);

    boolean isAlreadyRegisteredName(String name);

    UUID save(App app);

    void delete(App app);
}
