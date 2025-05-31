package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final FlightService flightService;
    private final TicketService ticketService;
    private final AuthorizedUserService authorizedUserService;
    private final CompanyService companyService;


    @GetMapping("/createFlight")
    public String createFlight(Model model) {
        model.addAttribute("flightDto", new FlightDto());
        return "company/createFlight";
    }

    @PostMapping("/createFlight")
    public String createFlight(@Valid FlightDto flightDto,
                               BindingResult bindingResult,
                               Model model) {
         if(bindingResult.hasErrors()) {
             return "company/createFlight";
         }
       long flightId = flightService.createFlight(flightDto);
         ticketService.createTickets(flightId);
        return "redirect:/company/profile";
    }
}