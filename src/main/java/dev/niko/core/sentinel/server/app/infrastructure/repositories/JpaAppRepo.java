package dev.niko.core.sentinel.server.app.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.AppRepo;
import dev.niko.core.sentinel.server.app.domain.exception.NotFoundException;
import dev.niko.core.sentinel.server.app.shared.mapper.AppMapper;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class JpaAppRepo implements AppRepo {

    private static final String NOT_EXISTS = "Id app not exists";
    
    private final SpringDataAppRepo repo;
    private final AppMapper mapper;

    @Override
    public Optional<App> findByUid(String uid) {
        return repo.findByUid(uid).map( m ->
            mapper.toDomain(m)
        ).map(Optional::of)
        .orElseThrow(
            () -> new NotFoundException(NOT_EXISTS)
        );
    }

    @Override
    public boolean isAlreadyRegisteredName(String name) {
        return repo.findByNameIgnoreCase(name).isPresent();
    }

    @Override
    public UUID save(App app) {
        return repo.save(
            mapper.toMap(app)
         ).getUid();
    }

    @Override
    public void delete(App app) {
        repo.delete(
            mapper.toMap(app)
        );
    }

}
