package dev.niko.core.sentinel.server.app.domain;

import dev.niko.core.sentinel.server.app.domain.exception.VersionFormatInvalidException;
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

    private static final String FORMAT_INVALID = "Format of version is invalid";

    private final Integer mayor;

    private final Integer minor;

    private final Integer patch;

    public Version() {
        this.mayor = 0;
        this.minor = 0;
        this.patch = 1;
    }

    public Version(String version) {
        
        if(!isValidFormat(version)) throw new VersionFormatInvalidException(FORMAT_INVALID);
        
        Map m = toMap(version);

        this.mayor = m.mayor;
        this.minor = m.minor;
        this.patch = m.patch;  
    }

    public static boolean isValidFormat(String str) {
        int mayor = 0, minor = 0, patch = 0;
        String[] args = split(str);

        if(args.length != 3) return false;

        try{
            mayor = Integer.valueOf(args[0]);
            minor = Integer.valueOf(args[1]);
            patch = Integer.valueOf(args[2]);

            if(mayor < 0 || minor < 0 || patch < 0) return false;

        }
        catch(NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static String[] split(String str) {
        return str.split("\\.");
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

    private Map toMap(String str) {
        String[] args = split(str);
        return new Map(
            Integer.valueOf(args[0]),
            Integer.valueOf(args[1]),
            Integer.valueOf(args[2])
        );
    }

    private record Map (int mayor, int minor, int patch) {}

}