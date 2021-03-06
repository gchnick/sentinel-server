package dev.niko.core.sentinel.server.application.shared;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(NON_NULL)
@Builder
public record Response(
    LocalDateTime timeStamp,
    int statusCode,
    HttpStatus status,
    String message,
    String accessToken,
    String path,
    Map<?, ?> errors,
    Map<?, ?> data) {    
}

