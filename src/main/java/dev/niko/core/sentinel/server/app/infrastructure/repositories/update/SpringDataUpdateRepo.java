package dev.niko.core.sentinel.server.app.infrastructure.repositories.update;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.niko.core.sentinel.server.app.infrastructure.mappings.update.UpdateMap;

public interface SpringDataUpdateRepo extends JpaRepository<UpdateMap, Long> {
    
}
