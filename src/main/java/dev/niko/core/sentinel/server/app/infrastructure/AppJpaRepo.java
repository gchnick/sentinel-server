package dev.niko.core.sentinel.server.app.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.niko.core.sentinel.server.app.domain.App;

public interface AppJpaRepo extends JpaRepository<App, Long> {

    Optional<App> findByNameIgnoreCase(String name);

    Optional<App> findByUid(String uid);
}
