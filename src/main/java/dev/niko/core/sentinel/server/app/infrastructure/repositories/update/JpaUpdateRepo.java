package dev.niko.core.sentinel.server.app.infrastructure.repositories.update;

import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.domain.update.UpdateRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaUpdateRepo implements UpdateRepo  {

    private final SpringDataUpdateRepo repo;

    @Override
    public UUID save(Update update) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
