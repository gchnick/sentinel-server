package dev.niko.core.sentinel.server.app.infrastructure.user.dependence;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.niko.core.sentinel.server.app.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.app.infrastructure.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final String USER_NOT_FOUND = "Email not found in the database";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        UserMap user = userService
            .findByUsername(email)
            .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
       
        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword(), true, true, true, true, user.getAuthorities());
    }
}
