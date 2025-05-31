package kg.attractor.projects.controlwork9.annotations.impl;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.projects.controlwork9.annotations.ValidEmail;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.checkUserInDB(email);
    }
}
