package dev.niko.core.sentinel.server.app.domain.update;

import dev.niko.core.sentinel.server.app.domain.Entity;
import dev.niko.core.sentinel.server.app.domain.version.Version;

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
}
