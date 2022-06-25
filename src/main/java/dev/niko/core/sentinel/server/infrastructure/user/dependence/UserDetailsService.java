package dev.niko.core.sentinel.server.infrastructure.user.dependence;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.infrastructure.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final String USER_NOT_FOUND = "Username not found in the database";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserMap user = userService
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
       
        return new User(user.getUsername() , user.getPassword(), true, true, true, true, user.getAuthorities());
    }
}
