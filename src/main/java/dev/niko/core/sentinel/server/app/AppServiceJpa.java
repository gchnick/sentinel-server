package dev.niko.core.sentinel.server.app;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.niko.core.sentinel.server.app.release.Release;
import dev.niko.core.sentinel.server.app.release.ReleaseDTO;
import dev.niko.core.sentinel.server.exception.BadRequestException;
import dev.niko.core.sentinel.server.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppServiceJpa implements AppService {

    private final String ALREADY_REGISTRED = "The name of app is already registered";
    private final String NOT_EXISTS = "Id category not exists";
    
    private final AppJpaRepo appRepo;

    @Override
    public App create(String name) {
        log.info("Saving new App: {}", name);
        nameShouldUnique(name);
        App app = new App(name);
        return appRepo.save(app);
    }

    @Override
    public App get(String uid) {
        log.info("Finding App by id: {}" , uid);
        return appRepo.findById(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
    }

    @Override
    public boolean isCurrent(String uid, String version) {
        log.info("Checked if it is current by id: {}" , uid);
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setName(String uid, String name) {
        App app = appRepo.findById(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        if(app.getName() != name) {
            log.info("App name changed, validating name");
            nameShouldUnique(name);
        }

        log.info("Changing app name by id: {} to {}" , uid, name);
        app.setName(name);
        appRepo.save(app);
    }

    @Override
    public Release dumpVersion(String uid, ReleaseDTO release) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String uid) {
        log.info("Deleting App by id: {} ", uid);
        appRepo.findById(uid).ifPresent(app -> {
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
