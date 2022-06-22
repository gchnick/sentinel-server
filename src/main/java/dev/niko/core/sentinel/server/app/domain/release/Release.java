package dev.niko.core.sentinel.server.app.domain.release;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.niko.core.sentinel.server.app.domain.LongEntity;
import dev.niko.core.sentinel.server.app.domain.version.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "releases")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Release extends LongEntity {

    @NotNull
    @Size(max = 11)
    @Valid
    @Embedded
    @AttributeOverride(
        name = "version",
        column = @Column(nullable = false, length = 11)
    )
    private Version version;
    
    @NotBlank
    @Column(nullable = false, length = 300)
    private String overview;
}
