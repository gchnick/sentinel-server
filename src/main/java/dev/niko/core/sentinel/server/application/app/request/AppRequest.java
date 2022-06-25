package dev.niko.core.sentinel.server.application.app.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AppRequest(
    @NotBlank(message = "Application name cannot be empty")
    @Size(message = "Application name must have a maximum of 100 characters" ,max = 100)
    String name
) {}
