package dev.niko.core.sentinel.server.app.infrastructure.repositories.update;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.domain.update.UpdateRepo;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.update.UpdateMapper;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class JpaUpdateRepo implements UpdateRepo  {

    private final SpringDataUpdateRepo repo;
    private final UpdateMapper mapper;

    @Override
    public UUID save(Update update) {
        return repo.save(
            mapper.toMap(update)
        ).getUid();
    }
    
}
