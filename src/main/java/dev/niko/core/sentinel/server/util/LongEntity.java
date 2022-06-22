package dev.niko.core.sentinel.server.util;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


/**
 * Base Entity Class
 */
@Getter
@Setter
@MappedSuperclass
public abstract class LongEntity {

    /**
     * Identificación única
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
}