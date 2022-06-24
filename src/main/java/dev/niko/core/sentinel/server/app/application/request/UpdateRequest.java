package dev.niko.core.sentinel.server.app.application.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.niko.core.sentinel.server.app.application.validation.Version;

public record UpdateRequest(
    @Version String version, 
    @NotBlank @Size(max = 300) String overview) {}
