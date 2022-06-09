package dev.niko.core.sentinel.server.app;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dev.niko.core.sentinel.server.app.release.Release;
import dev.niko.core.sentinel.server.util.UUIDEntity;
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
public class App extends UUIDEntity {

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

    @OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "uid_app")
    private List<Release> releases = new ArrayList<>();

    public App(String name, String currentVersion, String updateURL) {
        this.name = name;
        this.currentVersion = new Version(currentVersion);
        this.updateURL = updateURL;
    }
}