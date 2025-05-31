package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.TicketDto;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.model.TicketClass;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.repository.TicketClassRepository;
import kg.attractor.projects.controlwork9.repository.TicketRepository;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TicketMapper {
    private final UserService userService;
    private final FlightService flightService;
    private final TicketRepository ticketRepository;
    private final TicketClassRepository ticketClassRepository;

    public TicketDto toDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .booked(ticket.isBooked())
                .user(userService.findUserById(ticket.getUser().getId()))
                .flight(flightService.findById(ticket.getFlight().getId()))
                .ticketClass(ticket.getTicketClass().getName())
                .build();

    }
    public Ticket toEntity(TicketDto ticketDto) {
        TicketClass ticketsClass = ticketClassRepository.findByName(ticketDto.getTicketClass()).orElseThrow(()-> new NoSuchElementException("no such class"));
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
        ticket.setTicketClass(ticketsClass);
        return ticket;
    }


}
