package dev.niko.core.sentinel.server.version;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Version {

    private String version;

    @Transient
    @NotNull
    @Size(min= 0, max = 999)
    private final Integer mayor;

    @Transient
    @NotNull
    @Size(min= 0, max = 999)
    private final Integer minor;

    @Transient
    @NotNull
    @Size(min= 0, max = 999)
    private final Integer micro;


    @PrePersist
    public void prePersist() {
        version = this.toString();
    }

    public Version(String version) {
        String[] args = version.split("\\.");
        mayor = Integer.valueOf(args[0]);
        minor = Integer.valueOf(args[1]);
        micro = Integer.valueOf(args[2]);
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d", mayor, minor, micro);
    }
}