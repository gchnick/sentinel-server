package dev.niko.core.sentinel.server.infrastructure.app.dependence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.niko.core.sentinel.server.domain.AppRepo;
import dev.niko.core.sentinel.server.domain.AppServiceImp;

@Configuration
public class SpringAppService {

    @Bean
    public AppServiceImp appService(AppRepo appRepo) {
        return new AppServiceImp(appRepo);
    }    
}
