package dev.niko.core.sentinel.server.infrastructure.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;

@Repository
public interface SpringDataUserRepo extends JpaRepository<UserMap, Long> {

    Optional<UserMap> findBUsernameIgnoreCase(String username);
 
}
