package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.TicketService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final CompanyService companyService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TicketService ticketService;
    private final FlightService flightService;

    @GetMapping("/create")
    public String index(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "admin/create";
    }

    @PostMapping("/create")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/create";
        }else {
            userService.addUser(userDto);
            return "redirect:/users/profile";
        }
    }
    @GetMapping("companies")
    public String companies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "admin/companies";
    }

    @PostMapping("freeze/{id}")
    public String freeze(Model model
    , @PathVariable long id){
        companyService.freeze(id);
        return "redirect:/admin/companies";
    }
    @PostMapping("unfreeze/{id}")
    public String unfreeze(Model model
    , @PathVariable long id){
        companyService.unfreeze(id);
        return "redirect:/admin/companies";
    }

    @GetMapping("/tickets/{id}")
    public String ticket(@PathVariable Long id, Model model) {
        model.addAttribute("tickets", ticketService.getTicketsByFlightId(id));
        return "ticket/show";
    }

    @GetMapping("/flights/{id}")
    public String flight(@PathVariable Long id,
                         Model model) {
        model.addAttribute("flights", flightService.findByCompanyId(id));
        return "flight/show";
    }
}
