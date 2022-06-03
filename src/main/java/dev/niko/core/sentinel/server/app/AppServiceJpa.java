package dev.niko.core.sentinel.server.app;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.niko.core.sentinel.server.exception.BadRequestException;
import dev.niko.core.sentinel.server.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppServiceJpa implements AppService {

    private final String ALREADY_REGISTED = "The name of app is already registered";
    private final String NOT_EXISTS = "Id category not exists";
    
    private final AppJpaRepo appRepo;

    @Override
    public App create(App app) {
        String name = app.getName();
        log.info("Saving new App: {}", name);
        valid(name);
        return appRepo.save(app);
    }

    @Override
    public App get(String uid) {
        log.info("Finding App by id: {}" , uid);
        return appRepo.findById(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
    }

    @Override
    public App update(String uid, App appUpdated) {
        log.info("Updating App by id: {}" , uid);
        App app = appRepo.findById(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        if(app.getName() != appUpdated.getName()) {
            valid(appUpdated.getName());
        }
        app.setName(appUpdated.getName());
        app.setUpdateURL(appUpdated.getUpdateURL());
        return appRepo.save(app);
    }

    @Override
    public void delete(String uid) {
        log.info("Deleting App by id: {} ", uid);
        appRepo.findById(uid).ifPresent(app -> {
            appRepo.delete(app);
        });
    }

    private void valid(String name) {
        if(appRepo.findByName(name).isPresent()) {
            throw new BadRequestException(ALREADY_REGISTED);
        }
    }
}
