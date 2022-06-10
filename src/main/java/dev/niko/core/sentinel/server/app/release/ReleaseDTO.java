package dev.niko.core.sentinel.server.app.release;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import dev.niko.core.sentinel.server.version.validation.Version;

public record ReleaseDTO(
    @Version String version,
    @NotBlank @Max(300) String overview
) {}
