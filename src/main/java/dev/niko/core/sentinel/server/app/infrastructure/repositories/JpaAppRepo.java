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
    public Optional<App> findByUid(String uid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAlreadyRegisteredName(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public UUID save(App app) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(App app) {
        // TODO Auto-generated method stub
        
    }

}
