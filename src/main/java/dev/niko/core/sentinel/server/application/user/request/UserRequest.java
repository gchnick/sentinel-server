package dev.niko.core.sentinel.server.application.user.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.niko.core.sentinel.server.application.user.request.validation.Password;

public record UserRequest(
    @NotBlank(message = "Username cannot be empty")
    @Size(message = "Username must have a maximum of 100 characters", max = 100)
    String username,
    @Password String password,
    @NotNull(message = "User roles cannot be empty") List<String> roles
) {}
