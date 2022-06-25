package dev.niko.core.sentinel.server.application.app.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.niko.core.sentinel.server.application.app.validation.Version;

public record UpdateRequest(
    @Version String version, 
    @NotBlank @Size(max = 300) String overview) {}
