package kg.attractor.projects.controlwork9.controller;

import kg.attractor.projects.controlwork9.service.BookingService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final TicketService ticketService;
    private final BookingService bookingService;

    @GetMapping("/{ticketId}")
    public String bookTicket(@PathVariable Long ticketId, Model model) {
        ticketService.buy(ticketId);
        bookingService.createBooking(ticketId);
        return "redirect:/users/profile";
    }
}
