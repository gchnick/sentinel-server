package dev.niko.core.sentinel.server.app.infrastructure.dependencies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.niko.core.sentinel.server.app.domain.AppRepo;
import dev.niko.core.sentinel.server.app.domain.AppServiceImp;
import dev.niko.core.sentinel.server.app.domain.update.UpdateRepo;

@Configuration
public class SpringAppService {

    @Bean
    public AppServiceImp appService(AppRepo appRepo, UpdateRepo updateRepo) {
        return new AppServiceImp(appRepo, updateRepo);
    }    
}
