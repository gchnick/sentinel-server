package dev.niko.core.sentinel.server.app.infrastructure.mappings.update;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
    private String uid;

    public UpdateMap(Long id, String version, String overview, UUID uid) {
        this.id = id;
        this.version = version;
        this.overview = overview;
        this.uid = uid.toString();
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
