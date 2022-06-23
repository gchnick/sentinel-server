package dev.niko.core.sentinel.server.app.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.niko.core.sentinel.server.app.infrastructure.mappings.AppMap;

@Repository
public interface SpringDataAppRepo extends JpaRepository<AppMap, Long> {
    Optional<AppMap> findByUid(UUID uid);
    Optional<AppMap> findByNameIgnoreCase(String name);
}
