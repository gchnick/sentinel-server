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

    protected Long id;

    protected UUID uid;
}