package kg.attractor.projects.controlwork9.repository;

import kg.attractor.projects.controlwork9.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Flight> findByCompanyId(long companyId);

    @Query("SELECT f FROM Flight f WHERE " +
            "(:fromCity IS NULL OR f.fromCity = :fromCity) AND " +
            "(:toCity IS NULL OR f.toCity = :toCity) AND " +
            "(:departureTime IS NULL OR f.departureTime >= :departureTime) AND " +
            "(:arrivalTime IS NULL OR f.arrivalTime <= :arrivalTime)")
    List<Flight> findByCriteria(
            @Param("fromCity") String fromCity,
            @Param("toCity") String toCity,
            @Param("departureTime") LocalDateTime departureTime,
            @Param("arrivalTime") LocalDateTime arrivalTime
    );
} 