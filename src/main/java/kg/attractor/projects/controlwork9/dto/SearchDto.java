package kg.attractor.projects.controlwork9.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private Long id;
    
    @NotBlank(message = "Departure city is required")
    private String fromCity;
    
    @NotBlank(message = "Destination city is required")
    private String toCity;
    
    @NotNull(message = "Departure date is required")
    private LocalDate departureTime;
    @NotNull(message = "Arrival time is required")
    private LocalDate arrivalTime;
    
    @NotBlank(message = "Ticket class is required")
    private String ticketClass;
}
