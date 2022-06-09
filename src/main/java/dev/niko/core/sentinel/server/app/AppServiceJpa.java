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

    private final String ALREADY_REGISTRED = "The name of app is already registered";
    private final String NOT_EXISTS = "Id category not exists";
    
    private final AppJpaRepo appRepo;

    @Override
    public App create(AppDTO dto) {
        String name = dto.name();
        log.info("Saving new App: {}", name);
        nameShouldUnique(name);
        App newApp = toDAO(dto);
        return appRepo.save(newApp);
    }

    @Override
    public App get(String uid) {
        log.info("Finding App by id: {}" , uid);
        return appRepo.findById(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
    }

    @Override
    public App update(String uid, AppDTO dto) {
        App app = appRepo.findById(uid).orElseThrow(() -> new NotFoundException(NOT_EXISTS));
        if(app.getName() != dto.name()) {
            log.info("App name changed, validating name");
            nameShouldUnique(dto.name());
        }

        log.info("Updating App by id: {}" , uid);
        app.setName(dto.name());
        app.setUpdateURL(dto.updateURL());
        return appRepo.save(app);
    }

    @Override
    public void delete(String uid) {
        log.info("Deleting App by id: {} ", uid);
        appRepo.findById(uid).ifPresent(app -> {
            appRepo.delete(app);
        });
    }

    private App toDAO(AppDTO dto) {
        return new App(dto.name(), dto.currentVersion(), dto.updateURL());
    }

    private void nameShouldUnique(String name) {
        if(appRepo.findByNameIgnoreCase(name).isPresent()) {
            log.info("App name: \"{}\" is already registred", name);
            throw new BadRequestException(ALREADY_REGISTRED);
        }
    }
}
