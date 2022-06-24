package dev.niko.core.sentinel.server.app.infrastructure.app.mapping;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import dev.niko.core.sentinel.server.app.infrastructure.shared.BaseMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "updates")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UpdateMap extends BaseMap {

    @Column(nullable = false, length = 11)
    private String version;

    @Column(nullable = false, length = 300)
    private String overview;

    @Column(nullable = false, unique = true)
    private String uid;

    public UpdateMap(Long id, String version, String overview, String uid) {
        this.id = id;
        this.version = version;
        this.overview = overview;
        this.uid = uid;
    }

    @PrePersist
    void prePersist() {

        if(uid == null) {
            uid = UUID.randomUUID().toString();
        }
    }

    public void setUid(UUID uid) {
        this.uid = uid.toString();
    }

    public UUID getUid() {
        return UUID.fromString(uid);
    }
}
