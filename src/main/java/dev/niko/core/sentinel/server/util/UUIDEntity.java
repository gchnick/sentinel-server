package dev.niko.core.sentinel.server.util;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class UUIDEntity {

    /**
     * Identificación única
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uid", updatable = false, nullable = false, columnDefinition = "uid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
}