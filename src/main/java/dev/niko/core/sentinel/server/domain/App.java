package dev.niko.core.sentinel.server.domain;


import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.niko.core.sentinel.server.domain.exception.AppNameInvalidException;
import dev.niko.core.sentinel.server.domain.exception.VersionUpdateIsLessException;
import dev.niko.core.sentinel.server.domain.shared.AggregateRoot;
import dev.niko.core.sentinel.server.domain.exception.UpdateNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class App extends Entity implements AggregateRoot {
   
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
            throw new AppNameInvalidException();
        }

        this.name = name;
    }

    public boolean isCurrent(Version version) {
        return version.isGreaterOrEqual(currentVersion);
    }

    public boolean isCurrent(String version) {
        return isCurrent(new Version(version));
    }

    public void releaseUpdate(Update update) {

        if(!update.getVersion().isGreater(currentVersion)) {
            throw new VersionUpdateIsLessException();
        }

        updates = new ArrayList<>(updates);

        updates.add(update);
        currentVersion = update.getVersion();
    }

    public Update currentUpdateDatails() {
        return updates == null ? null : updates.stream()
        .filter( u -> currentVersion.equals(u.getVersion())).findFirst()
        .orElseThrow(()-> new UpdateNotFoundException());
    }

    public boolean updatesAvailable() {
        return !updates.isEmpty();
    }
}