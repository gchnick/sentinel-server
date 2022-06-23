package dev.niko.core.sentinel.server.app.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.AppRepo;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.AppMap;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaAppRepo implements AppRepo {
    
    private final SpringDataAppRepo repo;

    @Override
    public Optional<App> findByUid(UUID uid) {
        return Optional.of(repo.findByUid(uid).get().getEntity());
    }

    @Override
    public boolean isAlreadyRegisteredName(String name) {
        return repo.findByNameIgnoreCase(name).isPresent();
    }

    @Override
    public UUID save(App app) {
        AppMap _app = new AppMap(app);
        return repo.save(_app).getUid();
    }

    @Override
    public void delete(App app) {
        AppMap _app = new AppMap(app);
        repo.delete(_app); 
    }  
}
