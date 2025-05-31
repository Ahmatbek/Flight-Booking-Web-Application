package kg.attractor.projects.controlwork9.service.impl;

import kg.attractor.projects.controlwork9.dto.TicketClassDto;
import kg.attractor.projects.controlwork9.mapper.TicketsClassMapper;
import kg.attractor.projects.controlwork9.model.TicketClass;
import kg.attractor.projects.controlwork9.repository.TicketClassRepository;
import kg.attractor.projects.controlwork9.service.TicketClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TicketClassServiceImpl implements TicketClassService {
    private final TicketClassRepository ticketClassRepository;
    private final TicketsClassMapper ticketMapper;
    @Override
    public TicketClassDto findByName(String id) {
        TicketClass ticketClass = ticketClassRepository.findByName(id).orElseThrow(()-> new NoSuchElementException("not found"));
        return ticketMapper.toDto(ticketClass);
    }
}
