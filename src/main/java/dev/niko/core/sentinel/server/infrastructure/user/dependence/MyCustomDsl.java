package dev.niko.core.sentinel.server.infrastructure.user.dependence;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.niko.core.sentinel.server.application.security.auth.AuthenticationFilter;
import dev.niko.core.sentinel.server.application.security.request.RequestAuthorizationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

    private static final String FILTER_PROCESSES_URL = "/api/v1/auth/token";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        
        AuthenticationManager auth = http.getSharedObject(AuthenticationManager.class);

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(auth);
        authenticationFilter.setFilterProcessesUrl(FILTER_PROCESSES_URL);

        http.addFilter(authenticationFilter);
        http.addFilterBefore(new RequestAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
    
}
