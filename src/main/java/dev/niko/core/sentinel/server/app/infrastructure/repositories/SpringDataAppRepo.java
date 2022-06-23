package dev.niko.core.sentinel.server.app.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.niko.core.sentinel.server.app.domain.App;

@Repository
public interface SpringDataAppRepo extends JpaRepository<App, Long> {
    Optional<App> findByUid(UUID uid);
    Optional<App> findByNameIgnoreCase(String name);
}
