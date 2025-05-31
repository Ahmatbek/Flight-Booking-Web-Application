package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.TicketDto;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketMapper {
    private final UserService userService;
    private final FlightService flightService;
    public TicketDto toDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
//                .user(userService.findUserByEmail(ticket.getUser().getEmail()))
                .booked(ticket.isBooked())
                .flight(flightService.findById(ticket.getFlight().getId()))
                .build();

    }
    public Ticket toEntity(TicketDto ticketDto) {
        User user = new User();
        user.setId(ticketDto.getUser().getId());
        Flight flight = new Flight();
        flight.setId(ticketDto.getFlight().getId());

        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setPrice(ticketDto.getPrice());
        ticket.setFlight(flight);
        ticket.setBooked(ticketDto.isBooked());
        ticket.setUser(user);
        ticket.setSeatNumber(ticketDto.getSeatNumber());
        return ticket;
//        ticket.setTicketClass();
    }


}
