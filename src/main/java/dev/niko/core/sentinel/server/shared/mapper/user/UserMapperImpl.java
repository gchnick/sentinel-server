package dev.niko.core.sentinel.server.shared.mapper.user;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.application.user.request.UserRequest;
import dev.niko.core.sentinel.server.domain.security.UserRole;
import dev.niko.core.sentinel.server.infrastructure.user.mapping.RoleMap;
import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.shared.mapper.DataMapperException;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserMap toMap(UserRequest user) throws DataMapperException {
        List<RoleMap> roles = user.roles()
            .stream()
            .map(UserRole::valueOf)
            .map(RoleMap::new)
            .toList();
        return new UserMap(
            user.username(),
            user.password(),
            roles
        );
    }
    
}
