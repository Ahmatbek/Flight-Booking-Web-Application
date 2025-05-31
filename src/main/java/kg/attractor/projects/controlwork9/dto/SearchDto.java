package kg.attractor.projects.controlwork9.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
    
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    
    @NotBlank(message = "Ticket class is required")
    private String ticketClass;

    @NotBlank
    private String datetimeRange;
}
