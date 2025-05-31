package kg.attractor.projects.controlwork9.service.impl;

import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.TicketClassDto;
import kg.attractor.projects.controlwork9.dto.TicketDto;
import kg.attractor.projects.controlwork9.mapper.TicketMapper;
import kg.attractor.projects.controlwork9.mapper.UserMapper;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.model.TicketClass;
import kg.attractor.projects.controlwork9.repository.TicketRepository;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketClassService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;
    private final AuthorizedUserService authorizedUserService;
    private final UserMapper userMapper;
    private final FlightService flightService;
    private final TicketClassService ticketClassService;

    @Override
    public List<TicketDto> getTicketsByFlightId(long id){
        return ticketRepository.findByFlightId(id)
                .stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    @Override
    public boolean isAllowableToFreeze(long id, List<FlightDto> flights) {
        return flights.stream()
                .allMatch(flight -> {
                    List<TicketDto> tickets = ticketRepository.findByFlightId(flight.getId())
                            .stream()
                            .map(ticketMapper::toDto)
                            .toList();
                    return tickets.stream().noneMatch(TicketDto::isBooked);
                });
    }

    @Override
    public List<TicketDto> getFreeTickets(List<FlightDto> flights, String ticketType) {
        List<TicketDto> freeTickets = new ArrayList<>();
        TicketClassDto ticketClass = ticketClassService.findByName(ticketType);
        for (FlightDto flight : flights) {
            List<Ticket> tickets = ticketRepository.findByFlightIdAndBookedFalseAndTicketClassId(flight.getId(), ticketClass.getId());
            for (Ticket ticket : tickets) {
                freeTickets.add(ticketMapper.toDto(ticket));
            }
        }

        return freeTickets;
    }

    @Override
    public void buy(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(()-> new NoSuchElementException("not found"));
        if(!ticket.isBooked()) {
            ticket.setBooked(true);
            ticket.setUser(userMapper.toEntity(authorizedUserService.getAuthorizedUserDetails()));
            ticketRepository.save(ticket);
        }
    }

    @Override
    public TicketDto getTicket(long id) {
        Ticket tic = ticketRepository.findById(id).orElseThrow(()-> new NoSuchElementException("not found"));
        return ticketMapper.toDto(tic);
    }

    @Override
    public void createTickets(long flightId) {
        FlightDto flightDto =  flightService.findById(flightId);
           if(flightDto != null) {
               generateRandom10Tickets(flightId);
           }
    }


    private void generateRandom10Tickets(long flightId) {
        Flight flight = new Flight();
        flight.setId(flightId);

        TicketClassDto economyClass = ticketClassService.findByName("Economy");
        TicketClassDto businessClass = ticketClassService.findByName("Business");

        TicketClass normal = new TicketClass();
        normal.setId(economyClass.getId());

        TicketClass business = new TicketClass();
        business.setId(businessClass.getId());

        for (int i = 1; i <= 3; i++) {
            Ticket ticket = new Ticket();
            ticket.setSeatNumber("B" + i);
            ticket.setBooked(false);
            ticket.setFlight(flight);
            ticket.setTicketClass(business);
            ticket.setPrice(200.0);
            ticketRepository.save(ticket);
        }


        for (int i = 1; i <= 7; i++) {
            Ticket ticket = new Ticket();
            ticket.setSeatNumber("E" + i);
            ticket.setBooked(false);
            ticket.setFlight(flight);
            ticket.setTicketClass(normal);
            ticket.setPrice(100.0);
            ticketRepository.save(ticket);
        }
    }




}
