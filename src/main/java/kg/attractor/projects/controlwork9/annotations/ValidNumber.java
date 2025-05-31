package kg.attractor.projects.controlwork9.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kg.attractor.projects.controlwork9.annotations.impl.EmailValidator;
import kg.attractor.projects.controlwork9.annotations.impl.NumberValid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberValid.class)
public @interface ValidNumber {
    String message() default "Number is already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}