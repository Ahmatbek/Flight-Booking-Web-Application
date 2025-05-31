package kg.attractor.projects.controlwork9.repository;

import kg.attractor.projects.controlwork9.model.Authority;
import kg.attractor.projects.controlwork9.model.TicketClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketClassRepository extends JpaRepository<TicketClass, Long> {
    Optional<TicketClass> findByName(String name);
}
