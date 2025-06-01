package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.dto.PaginationDto;
import kg.attractor.projects.controlwork9.dto.SearchDto;
import kg.attractor.projects.controlwork9.dto.TicketDto;
import kg.attractor.projects.controlwork9.model.Flight;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String searchPost(@Valid SearchDto searchDto,
                                   BindingResult bindingResult,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   Model model) {
        return processSearch(searchDto, bindingResult, page, size, model);
    }

    @GetMapping("/flights/search")
    public String searchGet(@Valid SearchDto searchDto,
                                  BindingResult bindingResult,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {
        return processSearch(searchDto, bindingResult, page, size, model);
    }

    private String processSearch(SearchDto searchDto,
                                 BindingResult bindingResult,
                                 int page,
                                 int size,
                                 Model model) {
        if (!bindingResult.hasErrors()) {
            parseDateRange(searchDto);

            List<FlightDto> flights = flightService.showTickets(searchDto);
            List<TicketDto> allTickets = ticketService.getFreeTickets(flights, searchDto.getTicketClass());

            int totalItems = allTickets.size();
            int totalPages = (int) Math.ceil((double) totalItems / size);
            int start = Math.min(page * size, totalItems);
            int end = Math.min(start + size, totalItems);

            PaginationDto<TicketDto> pagination = new PaginationDto<>();
            pagination.setItems(allTickets.subList(start, end));
            pagination.setPage(page);
            pagination.setTotalPages(totalPages);
            pagination.setHasPreviousPage(page > 0);
            pagination.setHasNextPage(page < totalPages - 1);

            model.addAttribute("tickets", pagination);

        }

        model.addAttribute("searchDto", searchDto);
        return "search/search";
    }

    private void parseDateRange(SearchDto searchDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] times = searchDto.getDatetimeRange().split(" to ");
        searchDto.setDepartureTime(LocalDateTime.parse(times[0], formatter));
        searchDto.setArrivalTime(LocalDateTime.parse(times[1], formatter));
    }

}
