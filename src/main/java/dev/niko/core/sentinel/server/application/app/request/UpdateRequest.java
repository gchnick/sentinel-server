package dev.niko.core.sentinel.server.application.app.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.niko.core.sentinel.server.application.app.request.validation.Version;

public record UpdateRequest(
    @Version String version, 
    @NotBlank(message = "Update description cannot be empty")
    @Size(message = "Update description must have a maximum of 300 characters", max = 300)
    String overview) {}
