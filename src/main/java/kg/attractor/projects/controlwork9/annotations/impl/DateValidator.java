package kg.attractor.projects.controlwork9.annotations.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.projects.controlwork9.annotations.ValidDate;
import kg.attractor.projects.controlwork9.dto.FlightDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class DateValidator implements ConstraintValidator<ValidDate, FlightDto> {

    @Override
    public boolean isValid(FlightDto flightDto, ConstraintValidatorContext context) {
        if (flightDto == null) {
            return true;
        }

        LocalDate departureTime = flightDto.getDepartureTime();
        LocalDate arrivalTime = flightDto.getArrivalTime();

        if (departureTime == null || arrivalTime == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("It cannot be null.")
                    .addPropertyNode("departureTime")
                    .addConstraintViolation();

            context.buildConstraintViolationWithTemplate("It cannot be null.")
                    .addPropertyNode("arrivalTime")
                    .addConstraintViolation();
            return false; // Let @NotNull handle it if needed
        }

        boolean valid = true;

        // Validate order
        if (departureTime.isAfter(arrivalTime)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Departure time must be before arrival time.")
                    .addPropertyNode("departureTime")
                    .addConstraintViolation();

            context.buildConstraintViolationWithTemplate("Arrival time must be after departure time.")
                    .addPropertyNode("arrivalTime")
                    .addConstraintViolation();

            valid = false;
        }

        // Validate not in the past
        if (departureTime.isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Departure time cannot be in the past.")
                    .addPropertyNode("departureTime")
                    .addConstraintViolation();

            valid = false;
        }

        if (arrivalTime.isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Arrival time cannot be in the past.")
                    .addPropertyNode("arrivalTime")
                    .addConstraintViolation();

            valid = false;
        }

        return valid;
    }
}
