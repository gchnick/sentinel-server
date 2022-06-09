package dev.niko.core.sentinel.server.util;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Clase Entity Base
 */
@Data
@MappedSuperclass
public abstract class UUIDEntity implements Serializable {

    /**
     * Identificación única
     */
    @Id
    @GeneratedValue
    private UUID uid;

    public void setId(UUID id) {
        this.uid = id;
    }

    public UUID getId() {
        return uid;
    }
}