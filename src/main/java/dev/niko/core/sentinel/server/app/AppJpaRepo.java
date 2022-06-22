package dev.niko.core.sentinel.server.app;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppJpaRepo extends JpaRepository<App, UUID> {

    Optional<App> findByNameIgnoreCase(String name);

    Optional<App> findByUid(String uid);
}
