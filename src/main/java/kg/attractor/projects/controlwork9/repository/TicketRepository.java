package kg.attractor.projects.controlwork9.repository;

import kg.attractor.projects.controlwork9.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByFlightId(Long userId);
    List<Ticket> findByFlightIdAndBookedFalse(Long flightId);
    List<Ticket> findByFlightIdAndBookedFalseAndTicketClassId(Long flightId, Long id);
}
