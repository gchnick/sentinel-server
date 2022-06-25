package dev.niko.core.sentinel.server.application.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VersionValidator  implements ConstraintValidator<Version, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return dev.niko.core.sentinel.server.domain.Version.isValidFormat(value);
    }
    
}
