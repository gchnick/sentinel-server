package dev.niko.core.sentinel.server.shared.mapper.user;

import dev.niko.core.sentinel.server.application.user.request.UserRequest;
import dev.niko.core.sentinel.server.infrastructure.user.mapping.UserMap;
import dev.niko.core.sentinel.server.shared.mapper.DataMapperException;

public interface UserMapper {

    UserMap toMap(UserRequest user) throws DataMapperException;
    
}
