package dev.niko.core.sentinel.server.app;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.niko.core.sentinel.server.app.release.Release;
import dev.niko.core.sentinel.server.app.release.ReleaseDTO;
import dev.niko.core.sentinel.server.app.release.ReleaseJpaRepo;
import dev.niko.core.sentinel.server.exception.BadRequestException;
import dev.niko.core.sentinel.server.exception.NotFoundException;
import dev.niko.core.sentinel.server.version.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppServiceJpa implements AppService {

    private final String ALREADY_REGISTRED = "The name of app is already registered";
    private final String NOT_EXISTS = "Id category not exists";
    private final String ERROR_DUMP_VERSION = "Dump version is less that current version";
    
    private final AppJpaRepo appRepo;
    private final ReleaseJpaRepo releaseRepo;

    @Override
    public App create(String name) {
        log.info("Saving new App: {}", name);
        nameShouldUnique(name);
        App app = new App(name);
        return appRepo.save(app);
    }

    @Override
    public App get(UUID uid) {
        log.info("Finding App by id: {}" , uid);
        return appRepo.findByUid(uid.toString()).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
    }

    @Override
    public Collection<App> get() {
        log.info("Feching all");
        return appRepo.findAll();
    }

    @Override
    public boolean isCurrent(UUID uid, String version) {
        log.info("Checked if it is current by id: {}" , uid);
        App app = appRepo.findByUid(uid.toString()).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        return app.getCurrentVersion().isGreater(new Version(version));
    }

    @Override
    public void setName(UUID uid, String name) {
        App app = appRepo.findByUid(uid.toString()).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        if(app.getName() != name) {
            log.info("App name changed, validating name");
            nameShouldUnique(name);
        }

        log.info("Changing app name by id: {} to {}" , uid, name);
        app.setName(name);
        appRepo.save(app);
    }

    @Override
    public Release dumpVersion(App app, ReleaseDTO release) {
        log.info("Dumping version by app: {}" , app.getName());
        Version version = new Version(release.version());
        Release _release = new Release(version, release.overview());

        if(app.getCurrentVersion().isGreater(version)) {
            throw new BadRequestException(ERROR_DUMP_VERSION);
        }

        _release = releaseRepo.save(_release);
        app.addRelease(_release);
        app.setCurrentVersion(version);
        appRepo.save(app);
        return _release;
    }

    @Override
    public void delete(UUID uid) {
        log.info("Deleting App by id: {} ", uid);
        appRepo.findByUid(uid.toString()).ifPresent(app -> {
            appRepo.delete(app);
        });
    }

    private void nameShouldUnique(String name) {
        if(appRepo.findByNameIgnoreCase(name).isPresent()) {
            log.info("App name: \"{}\" is already registred", name);
            throw new BadRequestException(ALREADY_REGISTRED);
        }
    }
}
