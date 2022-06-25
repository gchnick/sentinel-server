package dev.niko.core.sentinel.server.app.infrastructure.user.service;

import java.util.Optional;

import dev.niko.core.sentinel.server.app.infrastructure.user.mapping.UserMap;

public interface UserService {

    UserMap create(UserMap user);

    Optional<UserMap> findByUsername(String username);

    void delete(Long id);
    
}
