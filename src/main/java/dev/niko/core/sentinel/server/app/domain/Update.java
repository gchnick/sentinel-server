package dev.niko.core.sentinel.server.app.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Update extends Entity {

    private Version version;
    
    private String overview;

    public Update(String version, String overview) {
        this.version = new Version(version);
        this.overview = overview;
    }

    public Update(Long id, String version, String overview, UUID uid) {
        this.id = id;
        this.version = new Version(version);
        this.overview = overview;
        this.uid = uid;
    }
}
