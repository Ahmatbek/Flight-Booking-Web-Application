package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.SearchDto;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final TicketService ticketService;

    @GetMapping()
    public String flight(Model model) {
        model.addAttribute("searchDto", new SearchDto());
        return "search/search";
    }
    @PostMapping("/flights/search")
    public String search(@Valid SearchDto searchDto,
                         BindingResult bindingResult,
                         Model model) {
        if(!bindingResult.hasErrors()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String[] times = searchDto.getDatetimeRange().split(" to ");
            searchDto.setDepartureTime(LocalDateTime.parse(times[0], formatter));
            searchDto.setArrivalTime(LocalDateTime.parse(times[1], formatter));

            List<FlightDto> flights = flightService.showTickets(searchDto);
            model.addAttribute("tickets", ticketService.getFreeTickets(flights, searchDto.getTicketClass()));
        }
        model.addAttribute("searchDto", searchDto);
        return "search/search";
    }
}
