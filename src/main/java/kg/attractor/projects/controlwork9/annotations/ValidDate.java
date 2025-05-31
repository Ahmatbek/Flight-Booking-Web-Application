package kg.attractor.projects.controlwork9.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kg.attractor.projects.controlwork9.annotations.impl.DateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate {
    String message() default "Invalid expFrom to expTo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}