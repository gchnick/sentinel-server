package dev.niko.core.sentinel.server.app.domain;

import dev.niko.core.sentinel.server.app.domain.exception.VersionFormartException;
import dev.niko.core.sentinel.server.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Esta clase es un Value Object de tipo String que representa
 * versiones de software
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Version implements ValueObject<String> {

    private static final String ERROR_MESSAGE = "Format of version is invalid";

    private final Integer mayor;

    private final Integer minor;

    private final Integer patch;

    public Version() {
        this.mayor = 0;
        this.minor = 0;
        this.patch = 1;
    }

    public Version(String version) {
        // TODO implementar revisar el formato con regex
        int mayor = 0, minor = 0, patch = 0;
        String[] args = version.split("\\.");

        try{
            mayor = Integer.valueOf(args[0]);
            minor = Integer.valueOf(args[1]);
            patch = Integer.valueOf(args[2]);

            if(mayor < 0 || minor < 0 || patch < 0) {
                throw new VersionFormartException(ERROR_MESSAGE);
            }

        }
        catch(NumberFormatException e) {
            throw new VersionFormartException(ERROR_MESSAGE.concat(String.format(" %s", e.getMessage())));
        }

        this.mayor = mayor;
        this.minor = minor;
        this.patch = patch;  
    }

    public boolean isGreater(Version version) {
        if(this.equals(version)) return false;

        return this.size() > version.size() ? true : false;
    }

    private int size() {
        return mayor * 1000000 + minor * 1000 + patch;
    }

    @Override
    public String value() {
        return String.format("%d.%d.%d", mayor, minor, patch);
    }

}