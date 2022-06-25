package dev.niko.core.sentinel.server.application.security;

import static dev.niko.core.sentinel.server.infrastructure.user.dependence.MyCustomDsl.customDsl;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

// import dev.niko.core.sentinel.server.infrastructure.user.dependence.UserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfigurer {
    
    // private final UserDetailsService userDetailsService;
    // private final PasswordEncoder passwordEncoder;

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.apply(customDsl());
        return http.build();
    }
    
}
