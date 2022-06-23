package dev.niko.core.sentinel.server.app.domain.update;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import dev.niko.core.sentinel.server.app.domain.version.validation.Version;

public record ReleaseDTO(
    @Version String version,
    @NotBlank @Max(300) String overview
) {}
