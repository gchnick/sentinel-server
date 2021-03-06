package dev.niko.core.sentinel.server.infrastructure.user.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class JpaUserRepo implements UserRepo {

    private final SpringDataUserRepo repo;

    @Override
    public Optional<UserMap> findByUsername(String username) {
        return repo.findByUsernameIgnoreCase(username);
    }

    @Override
    public boolean isAlreadyRegisteredUsername(String username) {
        return repo.findByUsernameIgnoreCase(username).isPresent();
    }

    @Override
    public UserMap save(UserMap user) {
        return repo.save(user);
    }

    @Override
    public void delete(String username) {
        repo.deleteByUsername(username);
    }
    
}
