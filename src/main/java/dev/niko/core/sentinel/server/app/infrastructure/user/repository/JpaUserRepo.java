package dev.niko.core.sentinel.server.app.infrastructure.user.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.niko.core.sentinel.server.app.infrastructure.user.mapping.UserMap;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class JpaUserRepo implements UserRepo {

    private final SpringDataUserRepo repo;

    @Override
    public Optional<UserMap> findBUsername(String username) {
        return repo.findBUsernameIgnoreCase(username);
    }

    @Override
    public UserMap save(UserMap user) {
        return repo.save(user);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
