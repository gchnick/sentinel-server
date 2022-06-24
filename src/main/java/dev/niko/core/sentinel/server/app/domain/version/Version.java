package dev.niko.core.sentinel.server.app.domain.version;

import dev.niko.core.sentinel.server.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Esta clase es un Value Object de tipo String que representa
 * versiones de software
 */
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
        return this.compareTo(version) > 0 ? true : false;
    }

    public int compareTo(Version version) {
        return this.size() - version.size();
    }

    private int size() {
        return mayor + minor + micro;
    }

    @Override
    public String value() {
        return String.format("%d.%d.%d", mayor, minor, micro);
    }

}