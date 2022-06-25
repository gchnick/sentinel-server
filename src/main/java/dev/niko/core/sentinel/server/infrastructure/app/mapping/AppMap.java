package dev.niko.core.sentinel.server.infrastructure.app.mapping;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import dev.niko.core.sentinel.server.infrastructure.shared.BaseMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "apps")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppMap extends BaseMap {

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

    public AppMap(Long id, String name, String currentVersion, String updateURL, List<UpdateMap> updates, String uid) {
        this.id = id;
        this.name = name;
        this.currentVersion = currentVersion;
        this.updateURL = updateURL;
        this.updates = updates;
        this.uid = uid;
    }
}
