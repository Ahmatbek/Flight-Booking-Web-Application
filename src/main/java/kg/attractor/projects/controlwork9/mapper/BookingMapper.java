package kg.attractor.projects.controlwork9.mapper;

import kg.attractor.projects.controlwork9.dto.BookingDto;
import kg.attractor.projects.controlwork9.model.Booking;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.service.TicketService;
import kg.attractor.projects.controlwork9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
@RequiredArgsConstructor
public class BookingMapper {
    private final TicketService ticketService;
    private final UserService userService;
    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .ticket(ticketService.getTicket(booking.getTicket().getId()))
                .user(userService.findUserById(booking.getUser().getId()))
                .time(booking.getTime())
                .build();
    }
    public Booking toEntity(BookingDto bookingDto) {
        User user   =  new User();
        user.setId(bookingDto.getUser().getId());

        Ticket ticket = new Ticket();
        ticket.setId(bookingDto.getTicket().getId());

        Booking booking = new Booking();
        booking.setId(bookingDto.getId());
        booking.setUser(user);
        booking.setTicket(ticket);
        booking.setTime(bookingDto.getTime());
        return booking;
    }
}
