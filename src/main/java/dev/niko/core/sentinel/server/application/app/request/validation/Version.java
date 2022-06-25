package dev.niko.core.sentinel.server.application.app.request.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = VersionValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface Version {

    public abstract java.lang.String message() default "Version format is invalid. Valid format [0-999].[0-999].[0-999]";
  
    public abstract  java.lang.Class<?>[] groups() default {};
  
    public abstract  java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
    
}
