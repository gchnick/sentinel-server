package dev.niko.core.sentinel.server.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * TODO Implementar la clase version como campo de la tabla
     */
    @NotNull
    @Size(max = 11)
    @Valid
    @Column(name = "current_version", nullable = false, length = 11)
    private Version currentVersion;

    @NotBlank
    @Size(max = 150)
    @Column(name = "update_URL", nullable = false, length = 150)
    private String updateURL;
}