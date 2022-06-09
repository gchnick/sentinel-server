package dev.niko.core.sentinel.server.version;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @version 0.2.0
 */
@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Version {

    private String version;

    private static final String ERROR_MESSAGE = "Format of version is invalid";

    @Transient
    private final Integer mayor;

    @Transient
    private final Integer minor;

    @Transient
    private final Integer micro;


    @PrePersist
    public void prePersist() {
        version = this.toString();
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

    @Override
    public String toString() {
        return String.format("%d.%d.%d", mayor, minor, micro);
    }

    // TODO implementar metodo para comprobar si una version es superior o inferior a otra
}