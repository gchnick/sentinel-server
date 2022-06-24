package dev.niko.core.sentinel.server.app.infrastructure.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.niko.core.sentinel.server.app.infrastructure.app.mapping.AppMap;

@Repository
public interface SpringDataAppRepo extends JpaRepository<AppMap, Long> {

    Optional<AppMap> findByUid(String uid);
    
    Optional<AppMap> findByNameIgnoreCase(String name);
}
