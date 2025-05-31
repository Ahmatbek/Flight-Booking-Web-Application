package kg.attractor.projects.controlwork9.repository;

import kg.attractor.projects.controlwork9.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long id);

}
