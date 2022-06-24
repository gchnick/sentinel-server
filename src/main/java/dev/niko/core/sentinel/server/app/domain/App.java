package dev.niko.core.sentinel.server.app.domain;


import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.niko.core.sentinel.server.app.domain.exception.NameAppInvalidException;
import dev.niko.core.sentinel.server.app.domain.exception.VersionUpdateIsLessException;
import dev.niko.core.sentinel.server.app.domain.exception.UpdateNotFoundException;
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

    private static final String NAME_INVALID = "Name app is invalid";
    private static final String VERSION_UPDATE_IS_LESS = "Version update is less that current version";
    private static final String UPDATE_NOT_FOUND = "Update details not found";

    private String name;

    private Version currentVersion;

    private String updateURL;

    private List<Update> updates;

    public App(String name) {
        setName(name);
        this.currentVersion = new Version();
        this.updateURL ="";
        this.updates = new ArrayList<>();
    }

    public App(Long id, String name, String currentVersion, String updateURL, List<Update> updates, UUID uid){
        this.id = id;
        setName(name);
        this.currentVersion = new Version(currentVersion);
        this.updateURL = updateURL;
        this.updates = updates;
        this.uid= uid;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new NameAppInvalidException(NAME_INVALID);
        }

        this.name = name;
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

        updates = new ArrayList<>(updates);

        updates.add(update);
        currentVersion = update.getVersion();
    }

    public Update currentUpdateDatails() {
        return updates == null ? null : updates.stream()
        .filter( u -> currentVersion.equals(u.getVersion())).findFirst()
        .orElseThrow(()-> new UpdateNotFoundException(UPDATE_NOT_FOUND));
    }
}