package kg.attractor.projects.controlwork9.service;

import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.SearchDto;

import java.util.List;

public interface FlightService {
    FlightDto findById(long id);
    List<FlightDto> findByCompanyId(long companyId);
    List<FlightDto> showTickets(SearchDto searchDto);
    long createFlight(FlightDto flightDto);

    Boolean checkNumberInDB(String email);
}
