package kg.attractor.projects.controlwork9.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.attractor.projects.controlwork9.annotations.ValidDate;
import kg.attractor.projects.controlwork9.annotations.ValidNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDate
public class FlightDto {
    private Long id;
    @NotBlank
    @ValidNumber
    private String number;
    @NotBlank
    private String fromCity;
    @NotBlank
    private String toCity;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private UserDto company;
}

