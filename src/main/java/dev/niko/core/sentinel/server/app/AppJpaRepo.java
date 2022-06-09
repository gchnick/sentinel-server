package dev.niko.core.sentinel.server.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppJpaRepo extends JpaRepository<App, String> {
    Optional<App> findByNameIgnoreCase(String name);
}
