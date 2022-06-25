package dev.niko.core.sentinel.server.app.infrastructure.user.repository;

import java.util.Optional;

import dev.niko.core.sentinel.server.app.infrastructure.user.mapping.UserMap;

public interface UserRepo {

    UserMap save(UserMap user);
    
    Optional<UserMap> findBUsername(String username);

    void delete(Long id);
}
