package dev.niko.core.sentinel.server.app.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.AppRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaAppRepo implements AppRepo {
    
    private final SpringDataAppRepo repo;

    @Override
    public Optional<App> findByUid(UUID uid) {
        return repo.findByUid(uid);
    }

    @Override
    public boolean isAlreadyRegisteredName(String name) {
        return repo.findByNameIgnoreCase(name).isPresent();
    }

    @Override
    public UUID save(App app) {
        return repo.save(app).getUid();
    }

    @Override
    public void delete(App app) {
        repo.delete(app);
        
    }  
}
