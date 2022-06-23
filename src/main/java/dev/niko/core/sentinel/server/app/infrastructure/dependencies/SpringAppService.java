package dev.niko.core.sentinel.server.app.infrastructure.dependencies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.niko.core.sentinel.server.app.domain.AppRepo;
import dev.niko.core.sentinel.server.app.domain.AppService;
import dev.niko.core.sentinel.server.app.domain.AppServiceImp;
import dev.niko.core.sentinel.server.app.domain.update.UpdateRepo;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SpringAppService {

    private final AppRepo appRepo;
    private final UpdateRepo updateRepo;


    @Bean
    public AppService springAppService() {
        return new AppServiceImp(appRepo, updateRepo);
    }
    
}
