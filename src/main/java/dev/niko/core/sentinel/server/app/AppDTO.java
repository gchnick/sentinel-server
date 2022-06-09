package dev.niko.core.sentinel.server.app;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AppDTO(
    @NotBlank @Size(max = 100) String name,
    @NotBlank @Size(max = 11) String currentVersion,
    @NotBlank @Size(max = 150) String updateURL) {}
