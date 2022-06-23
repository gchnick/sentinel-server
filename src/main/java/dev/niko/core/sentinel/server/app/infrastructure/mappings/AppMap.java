package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import dev.niko.core.sentinel.server.app.infrastructure.mappings.update.UpdateMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "apps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppMap {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "current_version", nullable = false, length = 11)
    private String currentVersion;

    @Column(name = "update_URL", nullable = false, length = 150)
    private String updateURL;
    
    @OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_app")
    private List<UpdateMap> updates = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String uid;


    @PrePersist()
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
