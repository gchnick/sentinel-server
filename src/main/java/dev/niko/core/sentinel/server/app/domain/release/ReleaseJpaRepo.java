package dev.niko.core.sentinel.server.app.domain.release;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseJpaRepo extends JpaRepository<Release, UUID> {
    
}