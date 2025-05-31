package kg.attractor.projects.controlwork9.controller;

import jakarta.validation.Valid;
import kg.attractor.projects.controlwork9.dto.BookingDto;
import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.repository.BookingRepository;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
import kg.attractor.projects.controlwork9.service.FlightService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthorizedUserService authorizedUserService;
    private final FlightService flightService;

    private final BookingRepository bookingRepository;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(@Valid UserDto user,
                           BindingResult bindingResult,
                           Model model) {
        if(!bindingResult.hasErrors()) {
            userService.addUser(user);
            return "redirect:/auth/login";
        }
        model.addAttribute("userDto", user);
        return "auth/register";
    }

    @GetMapping("/profile")
    public String showProfile(
            Model model
    ) {

        model.addAttribute("user", authorizedUserService.getAuthorizedUserDetails());
        model.addAttribute("bookings",bookingRepository.findAllByUserId(authorizedUserService.getAuthorizedUserDetails().getId()));
        model.addAttribute("flights", flightService.findByUserId(authorizedUserService.getAuthorizedUserDetails().getId()));

        return "profile/profile";
    }
}
