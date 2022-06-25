package dev.niko.core.sentinel.server.application.user;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.niko.core.sentinel.server.application.shared.Response;
import dev.niko.core.sentinel.server.application.user.request.UserRequest;
import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.infrastructure.user.service.UserService;
import dev.niko.core.sentinel.server.shared.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserResource {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping
    @Secured(ROLE_ADMIN)
    public ResponseEntity<Response> save(@RequestBody @Valid UserRequest user) {
        UserMap request = mapper.toMap(user);
        userService.create(request);
        return ResponseEntity.status(CREATED).body(
            Response.builder()
            .timeStamp(now())
            .message("User created successfully")
            .status(CREATED)
            .statusCode(CREATED.value())
            .build()
        );
    }

    @Secured(ROLE_ADMIN)
    @DeleteMapping("/{username}")
    public ResponseEntity<?> delete(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.noContent().build();
    }
    
}
