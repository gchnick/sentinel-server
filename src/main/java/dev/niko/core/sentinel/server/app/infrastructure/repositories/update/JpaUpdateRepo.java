package dev.niko.core.sentinel.server.app.infrastructure.repositories.update;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.niko.core.sentinel.server.app.domain.exception.NotFoundException;
import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.domain.update.UpdateRepo;
import dev.niko.core.sentinel.server.app.shared.mapper.update.UpdateMapper;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class JpaUpdateRepo implements UpdateRepo  {

    private static final String NOT_EXISTS = "Id update not exists";

    private final SpringDataUpdateRepo repo;
    private final UpdateMapper mapper;

    @Override
    public Optional<Update> findByUid(String uid) {
        return repo.findByUid(uid).map( m ->
            mapper.toDomain(m)
            ).map(Optional::of)
            .orElseThrow(
                () -> new NotFoundException(NOT_EXISTS)
            );
    }
}
