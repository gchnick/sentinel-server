package dev.niko.core.sentinel.server.app.domain;

import java.util.Optional;

import dev.niko.core.sentinel.server.shared.Repository;

public interface AppRepo extends Repository<App> {

    Optional<App> findByUid(String uid);

    boolean isAlreadyRegisteredName(String name);

    App save(App app);

    void delete(App app);
}
