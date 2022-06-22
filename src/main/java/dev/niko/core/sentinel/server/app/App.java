package dev.niko.core.sentinel.server.app;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.niko.core.sentinel.server.app.release.Release;
import dev.niko.core.sentinel.server.util.LongEntity;
import dev.niko.core.sentinel.server.version.Version;

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
public class App extends LongEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Embedded
    @AttributeOverride(
        name = "version",
        column = @Column(name = "current_version", nullable = false, length = 11)
    )
    private Version currentVersion;

    @Column(name = "update_URL", nullable = false, length = 150)
    private String updateURL;

    @JsonIgnore
    @OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_app")
    private List<Release> releases = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String uid;

    public App(String name) {
        this.name = name;
        this.currentVersion = new Version("0.0.1");
        this.updateURL ="";
    }

    public boolean addRelease(Release release) {
        return releases.add(release);
    }

    @PrePersist
    void uuid() {
        uid = UUID.randomUUID().toString();
    }
}