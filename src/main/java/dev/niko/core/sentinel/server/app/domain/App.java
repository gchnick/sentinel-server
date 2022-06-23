package dev.niko.core.sentinel.server.app.domain;


import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;

import dev.niko.core.sentinel.server.app.domain.exception.NameAppInvalidException;
import dev.niko.core.sentinel.server.app.domain.exception.VersionUpdateIsLessException;
import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.domain.version.Version;
import dev.niko.core.sentinel.server.shared.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class App extends Entity implements AggregateRoot {

    private static final String NAME_INVALID = "Name app is invalid.";
    private final String VERSION_UPDATE_IS_LESS = "Version update is less that current version";

    private String name;

    private Version currentVersion;

    private String updateURL;

    private List<Update> updates;

    public App(String name) {

        if(name == null || name.isBlank()) {
            throw new NameAppInvalidException(NAME_INVALID);
        }

        this.name = name;
        this.currentVersion = new Version();
        this.updateURL ="";
        this.updates = new ArrayList<>();
    }

    public boolean isCurrent(Version version) {
        return currentVersion.isGreater(version);
    }

    public boolean isCurrent(String version) {
        return isCurrent(new Version(version));
    }

    public void releaseUpdate(Update update) {

        if(isCurrent(update.getVersion())) {
            throw new VersionUpdateIsLessException(VERSION_UPDATE_IS_LESS);
        }

        updates.add(update);
        currentVersion = update.getVersion();
    }
}