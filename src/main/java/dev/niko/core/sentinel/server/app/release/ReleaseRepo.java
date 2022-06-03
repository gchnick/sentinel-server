package dev.niko.core.sentinel.server.app.release;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepo extends JpaRepository<Release, String> {
    
}