package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


/**
 * Base Entity Class
 */
@Getter
@Setter
public abstract class Entity {

    private Long id;

    private UUID uid;
}