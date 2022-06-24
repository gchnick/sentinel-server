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

    private final Integer micro;

    public Version() {
        this.mayor = 0;
        this.minor = 0;
        this.micro = 1;
    }

    public Version(String version) {
        // TODO implementar revisar el formato con regex
        int mayor = 0, minor = 0, micro = 0;
        String[] args = version.split("\\.");

        try{
            mayor = Integer.valueOf(args[0]);
            minor = Integer.valueOf(args[1]);
            micro = Integer.valueOf(args[2]);

            if(mayor < 0 || minor < 0 || micro < 0) {
                throw new VersionFormartException(ERROR_MESSAGE);
            }

        }
        catch(NumberFormatException e) {
            throw new VersionFormartException(ERROR_MESSAGE.concat(String.format(" %s", e.getMessage())));
        }

        this.mayor = mayor;
        this.minor = minor;
        this.micro = micro;  
    }

    public boolean isGreater(Version version) {
        if(this.equals(version)) return false;
        if(mayor > version.mayor) return true;
        if(minor > version.minor) return true;
        if(micro > version.micro) return true;
        return false;
    }

    @Override
    public String value() {
        return String.format("%d.%d.%d", mayor, minor, micro);
    }

}