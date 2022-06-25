package dev.niko.core.sentinel.server.infrastructure.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.infrastructure.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Override
    public UserMap create(UserMap user) {
        log.info("Saving new user");
        return repo.save(user);
    }

    @Override
    public Optional<UserMap> findByUsername(String username) {
        log.info("Finding user by username: {}", username);
        return repo.findByUsername(username);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting user by id: {}", id);
        repo.delete(id);
        
    }
}
