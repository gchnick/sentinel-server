package dev.niko.core.sentinel.server.app.infrastructure.repositories.update;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.niko.core.sentinel.server.app.infrastructure.mappings.UpdateMap;

@Repository
public interface SpringDataUpdateRepo extends JpaRepository<UpdateMap, Long> {
    
}
