package kg.attractor.projects.controlwork9.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {

    private Long id;
    @NotNull
    private UserDto user;
    @NotNull
    private TicketDto ticket;
    private LocalDateTime time;
}

