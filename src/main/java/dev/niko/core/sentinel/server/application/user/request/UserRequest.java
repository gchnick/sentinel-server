package dev.niko.core.sentinel.server.application.user.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserRequest(
    @NotBlank String username,
    @NotBlank String password,
    @NotNull List<String> roles
) {}
