package dev.niko.core.sentinel.server.infrastructure.user.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.niko.core.sentinel.server.domain.exception.BadRequestException;
import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.infrastructure.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ALREADY_REGISTRED = "Username is already registered";

    private final UserRepo repo;
    private final PasswordEncoder encoder;

    @Override
    public UserMap create(UserMap user) {
        log.info("Saving new user");
        usernameUnique(user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
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

    private void usernameUnique(String username) {
        if(repo.isAlreadyRegisteredUsername(username)) {
            log.info("App name: \"{}\" is already registred", username);
            throw new BadRequestException(ALREADY_REGISTRED);
        }
    }
}
