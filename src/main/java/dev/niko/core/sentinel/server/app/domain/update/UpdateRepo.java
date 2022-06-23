package dev.niko.core.sentinel.server.app.domain.update;

import java.util.UUID;

public interface UpdateRepo {
    
    UUID save(Update update);
}
