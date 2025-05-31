package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.FlightDto;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
import kg.attractor.projects.controlwork9.service.CompanyService;
import kg.attractor.projects.controlwork9.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final FlightService flightService;
    private final AuthorizedUserService authorizedUserService;
    private final CompanyService companyService;

//    @GetMapping("/profile")
//    public String profile(Model model) {
//        Company company = companyService.findByEmail(principal.getName());
//        model.addAttribute("company", company);
//        model.addAttribute("flights", flightService.getCompanyFlights(company.getId()));
//        return "company/profile";
//    }

//    @PostMapping("/upload-logo")
//    public String uploadLogo(@RequestParam("file") MultipartFile file, Principal principal) {
//        companyService.uploadLogo(principal.getName(), file);
//        return "redirect:/company/profile";
//    }
//
//    @PostMapping("/createFlight")
//    public String createFlight(@Valid FlightDto flightDto) {
//        Company company = companyService.findByEmail(authorizedUserService.getAuthorizedUserDetails().getEmail());
//        flightService.createFlight(flightDto, company.getId());
//        return "redirect:/company/profile";
//    }
}