package dev.niko.core.sentinel.server.version;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

import dev.niko.core.sentinel.server.app.domain.version.Version;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class VersionShould {

    @Test
    void compare_with_another_version_and_return_integer_greanter_that_zero() {
        // Arrange
        Version version = new Version("1.3.4");
        Version otherVersion = new Version("1.2.4");

        // Act
        int compare = version.compareTo(otherVersion);

        // Assert
        assertTrue(compare > 0);
    }

    @Test
    void compare_with_another_version_and_return_zero_when_equal() {
        // Arrange
        Version version = new Version("1.3.4");
        Version otherVersion = new Version("1.3.4");

        // Act
        int compare = version.compareTo(otherVersion);

        // Assert
        assertTrue(compare == 0);
    }

    @Test
    void compare_with_another_version_and_return_integer_less_that_zero() {
        // Arrange
        Version version = new Version("0.3.4");
        Version otherVersion = new Version("1.3.4");

        // Act
        int compare = version.compareTo(otherVersion);

        // Assert
        assertTrue(compare < 0);
    }

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

    // FIXME bug para identificar correctamente una version mayor
    @Test
    void return_true_if_greater_when_the_sum_is_greater() {
        // Arrange
        Version version = new Version("1.0.0");
        Version otherVersion = new Version("0.4.7");

        // Act
        boolean isGreater = version.isGreater(otherVersion);

        // Assert
        assertTrue(isGreater);
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
