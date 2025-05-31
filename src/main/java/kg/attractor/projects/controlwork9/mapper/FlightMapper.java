package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.FlightDto;
//import kg.attractor.projects.controlwork9.model.Company;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.UserService;
import kg.attractor.projects.controlwork9.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {

    private UserService userServiceImpl;

    @Autowired
    public FlightMapper(@Lazy UserService userService) {
        this.userServiceImpl = userService;
    }
    public Flight toEntity(FlightDto flight) {
        User company = new User();
        company.setId(flight.getCompany().getId());
        Flight flight1 = new Flight();
        flight1.setId(flight.getId());
        flight1.setCompany(company);
        flight1.setNumber(flight.getNumber());
        flight1.setDepartureTime(flight.getDepartureTime());
        flight1.setArrivalTime(flight.getArrivalTime());
        flight1.setToCity(flight.getToCity());
        flight1.setFromCity(flight.getFromCity());
        return flight1;

    }
    public FlightDto toDto(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .number(flight.getNumber())
                .arrivalTime(flight.getArrivalTime())
                .departureTime(flight.getDepartureTime())
                .toCity(flight.getToCity())
                .fromCity(flight.getFromCity())
                .company(userServiceImpl.findUserById(flight.getCompany().getId()))
                .build();
    }
}
