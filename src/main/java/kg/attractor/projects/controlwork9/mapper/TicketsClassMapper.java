package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.TicketClassDto;
import kg.attractor.projects.controlwork9.model.TicketClass;
import org.springframework.stereotype.Service;

@Service
public class TicketsClassMapper {
    public TicketClass toEntity(TicketClassDto ticketClass) {
        TicketClass ticketClassEntity = new TicketClass();
        ticketClassEntity.setId(ticketClass.getId());
        ticketClassEntity.setName(ticketClass.getName());
        return ticketClassEntity;

    }
    public TicketClassDto toDto(TicketClass ticketClass) {
        TicketClassDto ticketClassDto = new TicketClassDto();
        ticketClassDto.setId(ticketClass.getId());
        ticketClassDto.setName(ticketClass.getName());
        return ticketClassDto;
    }
}
