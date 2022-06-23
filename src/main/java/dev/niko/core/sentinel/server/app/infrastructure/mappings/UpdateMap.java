package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dev.niko.core.sentinel.server.app.domain.update.Update;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "updates")
@Data
@NoArgsConstructor
public class UpdateMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String version;

    @Column(nullable = false, length = 300)
    private String overview;

    @Column(nullable = false, unique = true)
    private UUID uid;

    @Transient
    private Update entity;

    public UpdateMap(Long id, String version, String overview, UUID uid, Update entity) {
        this.id = id;
        this.version = version;
        this.overview = overview;
        this.uid = uid;
        this.entity = entity;
    }

    public Memento createMemento() {
        return new UpdateMemento(entity, this);
    }

    public Update getEntity() {
        return entity;
    }
}
