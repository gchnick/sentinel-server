package dev.niko.core.sentinel.server.app.domain.version.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VersionValidator  implements ConstraintValidator<Version, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return true;
    }
    
}