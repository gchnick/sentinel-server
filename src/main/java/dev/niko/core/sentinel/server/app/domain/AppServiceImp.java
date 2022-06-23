package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

import dev.niko.core.sentinel.server.app.domain.exception.BadRequestException;
import dev.niko.core.sentinel.server.app.domain.exception.NotFoundException;
import dev.niko.core.sentinel.server.app.domain.update.ReleaseDTO;
import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.domain.update.UpdateRepo;
import dev.niko.core.sentinel.server.app.domain.version.Version;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AppServiceImp implements AppService {

    private final String ALREADY_REGISTRED = "The name of app is already registered";
    private final String NOT_EXISTS = "Id category not exists";
    
    private final AppRepo appRepo;
    private final UpdateRepo updateRepo;

    @Override
    public UUID create(String name) {
        log.info("Saving new App: {}", name);
        nameShouldUnique(name);
        App app = new App(name);
        return appRepo.save(app);
    }

    @Override
    public App get(UUID uid) {
        log.info("Finding App by id: {}" , uid);
        return appRepo.findByUid(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
    }

    @Override
    public boolean isCurrent(UUID uid, String version) {
        log.info("Checked if it is current by id: {}" , uid);
        App app = appRepo.findByUid(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        return app.isCurrent(version);
    }

    @Override
    public void setName(UUID uid, String name) {
        App app = appRepo.findByUid(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        if(app.getName() != name) {
            log.info("App name changed, validating name");
            nameShouldUnique(name);
        }

        log.info("Changing app name by id: {} to {}" , uid, name);
        app.setName(name);
        appRepo.save(app);
    }

    @Override
    public UUID releaseUpdate(App app, ReleaseDTO update) {
        log.info("Release update by app: {}" , app.getName());
        Version version = new Version(update.version());
        Update _update = new Update(version, update.overview());

        app.releaseUpdate(_update);

        UUID uid = updateRepo.save(_update);
        appRepo.save(app);
        return uid;
    }

    @Override
    public void delete(UUID uid) {
        log.info("Deleting App by id: {} ", uid);
        appRepo.findByUid(uid).ifPresent(app -> {
            appRepo.delete(app);
        });
    }

    private void nameShouldUnique(String name) {
        if(appRepo.isAlreadyRegisteredName(name)) {
            log.info("App name: \"{}\" is already registred", name);
            throw new BadRequestException(ALREADY_REGISTRED);
        }
    }
}
