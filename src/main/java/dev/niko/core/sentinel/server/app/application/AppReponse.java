package dev.niko.core.sentinel.server.app.application;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(NON_NULL)
@Builder
public record AppReponse(
    String uid,
    String name,
    String currentVersion,
    String overview,
    String updateURL
    ) {}
