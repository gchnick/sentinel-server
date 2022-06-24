package dev.niko.core.sentinel.server.app.domain.update;

import java.util.Optional;

// TODO remove this repo
public interface UpdateRepo {
    
    Optional<Update> findByUid(String uid);
}
