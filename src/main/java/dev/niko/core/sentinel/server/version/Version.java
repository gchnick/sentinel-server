package dev.niko.core.sentinel.server.version;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Version {

    @NotNull
    @Size(min= 0, max = 999)
    private final Integer mayor;

    @NotNull
    @Size(min= 0, max = 999)
    private final Integer minor;

    @NotNull
    @Size(min= 0, max = 999)
    private final Integer micro;

    public Version(String version) {
        String[] args = version.split("\\.");
        mayor = Integer.valueOf(args[0]);
        minor = Integer.valueOf(args[1]);
        micro = Integer.valueOf(args[2]);
    }

    public Version mayor() {
        return new Version(++mayor, minor, micro);
    }

    public Version minor() {
        return new Version(mayor, ++minor, micro);
    }

    public Version micro() {
        return new Version(mayor, minor, ++micro);
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d", mayor, minor, micro);
    }
}