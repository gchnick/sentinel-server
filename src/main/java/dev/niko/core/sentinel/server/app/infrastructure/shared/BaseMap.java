package dev.niko.core.sentinel.server.app.infrastructure.shared;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@MappedSuperclass
public abstract class BaseMap implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;
    
}
