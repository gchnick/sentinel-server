package dev.niko.core.sentinel.server.version;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

import dev.niko.core.sentinel.server.app.domain.Version;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class VersionShould {

    @Test
    void return_true_if_greater() {
        // Arrange
        Version version = new Version("1.3.4");
        Version otherVersion = new Version("1.2.4");

        // Act
        boolean isGreater = version.isGreater(otherVersion);

        // Assert
        assertTrue(isGreater);
    }
    
    @Test
    void return_true_if_greater_when_the_sum_is_greater() {
        // Arrange
        Version mayor = new Version("1.0.0");
        Version otherVersion = new Version("0.4.7");
        Version minor = new Version("1.5.0");
        Version otherVersion1 = new Version("1.4.7");
        Version micro = new Version("2.3.45");
        Version otherVersion3 = new Version("2.3.7");

        // Act
        boolean isGreater = mayor.isGreater(otherVersion);
        boolean isGreater1 = minor.isGreater(otherVersion1);
        boolean isGreater3 = micro.isGreater(otherVersion3);

        // Assert
        assertTrue(isGreater);
        assertTrue(isGreater1);
        assertTrue(isGreater3);
    }

    @Test
    void return_false_if_equal() {
        // Arrange
        Version version = new Version("1.3.4");
        Version otherVersion = new Version("1.3.4");

        // Act
        boolean isGreater = version.isGreater(otherVersion);

        // Assert
        assertFalse(isGreater);
    }

    @Test
    void return_false_if_less() {
        // Arrange
        Version version = new Version("0.3.4");
        Version otherVersion = new Version("1.3.4");

        // Act
        boolean isGreater = version.isGreater(otherVersion);

        // Assert
        assertFalse(isGreater);
    }
}
