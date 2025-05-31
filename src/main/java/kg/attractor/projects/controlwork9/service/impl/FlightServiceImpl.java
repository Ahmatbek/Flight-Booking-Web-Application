package kg.attractor.projects.controlwork9.service.impl;

import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.SearchDto;
import kg.attractor.projects.controlwork9.mapper.CompanyMapper;
import kg.attractor.projects.controlwork9.mapper.FlightMapper;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.repository.FlightRepository;
import kg.attractor.projects.controlwork9.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final CompanyMapper companyMapper;
    @Override
    public FlightDto findById(long id) {
        Flight flight =  flightRepository.findById(id).orElseThrow(()-> new NoSuchElementException("NO such flight"));
        return flightMapper.toDto(flight);
    }

    @Override
    public List<FlightDto> findByCompanyId(long companyId) {
        return flightRepository.findByCompanyId(companyId)
                .stream()
                .map(flightMapper::toDto)
                .toList();
    }

    @Override
    public List<FlightDto> showTickets(SearchDto searchDto) {
        FlightDto searchCriteria = flightDto(searchDto);
        List<Flight> flights = flightRepository.findByCriteria(
                searchCriteria.getFromCity(),
                searchCriteria.getToCity(),
                searchCriteria.getDepartureTime(),
                searchCriteria.getArrivalTime()
        );

        return flights.stream()
                .map(flight -> FlightDto.builder()
                        .id(flight.getId())
                        .number(flight.getNumber())
                        .fromCity(flight.getFromCity())
                        .toCity(flight.getToCity())
                        .departureTime(flight.getDepartureTime())
                        .arrivalTime(flight.getArrivalTime())
                        .company(companyMapper.toDto(flight.getCompany()))
                        .build())
                .toList();
    }

    private FlightDto flightDto(SearchDto searchDto) {
        return FlightDto.builder()
                .fromCity(searchDto.getFromCity())
                .toCity(searchDto.getToCity())
                .departureTime(searchDto.getDepartureTime().atStartOfDay())
                .arrivalTime(searchDto.getArrivalTime().atStartOfDay())
                .build();
    }

}
