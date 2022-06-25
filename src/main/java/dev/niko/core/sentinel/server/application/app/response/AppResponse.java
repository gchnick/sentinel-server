package dev.niko.core.sentinel.server.application.app.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(NON_NULL)
@Builder
public record AppResponse(
    String uid,
    String name,
    String currentVersion,
    String overview,
    String updateURL
    ) {}
