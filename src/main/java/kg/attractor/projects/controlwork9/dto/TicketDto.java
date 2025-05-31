package kg.attractor.projects.controlwork9.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.model.TicketClass;
import kg.attractor.projects.controlwork9.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;

    private String seatNumber;

    private double price;

    private FlightDto flight;

    private TicketClassDto ticketClass;

    private UserDto user;

    private boolean booked;
}
