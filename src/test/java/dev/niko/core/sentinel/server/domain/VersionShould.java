package dev.niko.core.sentinel.server.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class VersionShould {

    @Test
    void check_if_greater() {
        // Arrange
        Version version = new Version("1.3.4");
        Version otherVersion = new Version("1.2.4");

        // Act
        boolean isGreater = version.isGreaterOrEqual(otherVersion);

        // Assert
        assertTrue(isGreater);
    }
    
    @Test
    void check_if_major_is_higher() {
        // Arrange
        Version mayor = new Version("1.0.0");
        Version otherVersion = new Version("0.4.7");

        // Act
        boolean isGreater = mayor.isGreaterOrEqual(otherVersion);
        
        // Assert
        assertTrue(isGreater);
    }

    @Test
    void check_if_menor_is_higher() {
        // Arrange
        Version minor = new Version("1.5.0");
        Version otherVersion = new Version("1.4.7");

        // Ac
        boolean isGreater = minor.isGreaterOrEqual(otherVersion);
       
        // Assert
        assertTrue(isGreater);
    }

    @Test
    void check_if_menor_is_higher_when_is_top_version() {
        // Arrange
        Version minor = new Version("0.0.47");
        Version otherVersion = new Version("0.1.0");

        // Ac
        boolean isGreater = minor.isGreaterOrEqual(otherVersion);
       
        // Assert
        assertFalse(isGreater);
    }

    @Test
    void check_if_patch_is_higher() {
        // Arrange
        Version patch = new Version("2.3.45");
        Version otherVersion = new Version("2.3.7");

        // Act
        boolean isGreater = patch.isGreaterOrEqual(otherVersion);

        // Assert
        assertTrue(isGreater);
    }

    @Test
    void check_if_patch_is_higher_2() {
        // Arrange
        Version version = new Version("0.0.45");
        Version otherVersion = new Version("0.0.31");

        // Act
        boolean isGreater = version.isGreaterOrEqual(otherVersion);

        // Assert
        assertTrue(isGreater);
    }

    @Test
    void check_if_is_equal_then_is_not_greater() {
        // Arrange
        Version version = new Version("1.3.4");
        Version otherVersion = new Version("1.3.4");

        // Act
        boolean isGreater = version.isGreaterOrEqual(otherVersion);

        // Assert
        assertFalse(isGreater);
    }

    @Test
    void check_if_less_then_is_not_greater() {
        // Arrange
        Version version = new Version("0.3.4");
        Version otherVersion = new Version("1.3.4");

        // Act
        boolean isGreater = version.isGreaterOrEqual(otherVersion);

        // Assert
        assertFalse(isGreater);
    }

}
