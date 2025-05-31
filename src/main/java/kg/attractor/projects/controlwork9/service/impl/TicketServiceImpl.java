package kg.attractor.projects.controlwork9.service.impl;

import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.TicketDto;
import kg.attractor.projects.controlwork9.mapper.TicketMapper;
import kg.attractor.projects.controlwork9.mapper.UserMapper;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.repository.TicketRepository;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
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
    public List<TicketDto> getFreeTickets(List<FlightDto> flights) {
        List<TicketDto> freeTickets = new ArrayList<>();

        for (FlightDto flight : flights) {
            List<Ticket> tickets = ticketRepository.findByFlightIdAndBookedFalse(flight.getId());
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


}
