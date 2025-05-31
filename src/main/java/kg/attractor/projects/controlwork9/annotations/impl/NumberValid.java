package kg.attractor.projects.controlwork9.annotations.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.projects.controlwork9.annotations.ValidEmail;
import kg.attractor.projects.controlwork9.annotations.ValidNumber;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberValid implements ConstraintValidator<ValidNumber, String> {
    private final FlightService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.checkNumberInDB(email);
    }
}
