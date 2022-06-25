package dev.niko.core.sentinel.server.application.user.request;

import java.util.List;

public record UserRequest(
    String username,
    String password,
    List<String> roles
) {}
