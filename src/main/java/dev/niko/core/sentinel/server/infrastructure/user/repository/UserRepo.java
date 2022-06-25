package dev.niko.core.sentinel.server.infrastructure.user.repository;

import java.util.Optional;

import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;

public interface UserRepo {

    UserMap save(UserMap user);
    
    Optional<UserMap> findByUsername(String username);

    void delete(Long id);
}
