package kg.attractor.projects.controlwork9.service;

import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> getTicketsByFlightId(long id);

    boolean isAllowableToFreeze(long id, List<FlightDto> flights);

    List<TicketDto> getFreeTickets(List<FlightDto> flights);

    void buy(long id);

    TicketDto getTicket(long id);

    void createTickets(long flightId);
}
