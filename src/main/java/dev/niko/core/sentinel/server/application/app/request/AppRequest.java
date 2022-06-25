package dev.niko.core.sentinel.server.application.app.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AppRequest(
    @NotBlank @Size(max = 100) String name
) {}
