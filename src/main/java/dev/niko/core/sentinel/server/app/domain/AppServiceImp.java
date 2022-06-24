package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

import dev.niko.core.sentinel.server.app.domain.exception.BadRequestException;
import dev.niko.core.sentinel.server.app.domain.update.Update;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AppServiceImp implements AppService {

    private static final String ALREADY_REGISTRED = "The name of app is already registered";
    
    private final AppRepo appRepo;

    @Override
    public UUID create(String name) {
        log.info("Saving new App: {}", name);
        nameShouldUnique(name);
        App app = new App(name);
        app = appRepo.save(app);
        return app.getUid();
    }

    @Override
    public App get(UUID uid) {
        log.info("Finding App by id: {}" , uid);
        return appRepo.findByUid(uid.toString()).get();
    }

    @Override
    public boolean isCurrent(UUID uid, String version) {
        log.info("Checked if it is current by id: {}" , uid);
        App app = appRepo.findByUid(uid.toString()).get();
        return app.isCurrent(version);
    }

    @Override
    public void setName(UUID uid, String name) {
        App app = appRepo.findByUid(uid.toString()).get();
        if(app.getName() != name) {
            log.info("App name changed, validating name");
            nameShouldUnique(name);
        }

        log.info("Changing app name by id: {} to {}" , uid, name);
        app.setName(name);
        appRepo.save(app);
    }

    @Override
    public void releaseUpdate(App app, Update update) {
        log.info("Release update by app: {}" , app.getName());
        app.releaseUpdate(update);

        appRepo.save(app);
    }

    @Override
    public void delete(UUID uid) {
        log.info("Deleting App by id: {} ", uid);
        appRepo.findByUid(uid.toString()).ifPresent(app -> {
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
