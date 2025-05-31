package kg.attractor.projects.controlwork9.service.impl;

import kg.attractor.projects.controlwork9.dto.BookingDto;
import kg.attractor.projects.controlwork9.dto.TicketDto;
import kg.attractor.projects.controlwork9.dto.UserDto;
import kg.attractor.projects.controlwork9.mapper.BookingMapper;
import kg.attractor.projects.controlwork9.model.Booking;
import kg.attractor.projects.controlwork9.model.Ticket;
import kg.attractor.projects.controlwork9.model.User;
import kg.attractor.projects.controlwork9.repository.BookingRepository;
import kg.attractor.projects.controlwork9.repository.TicketRepository;
import kg.attractor.projects.controlwork9.service.AuthorizedUserService;
import kg.attractor.projects.controlwork9.service.BookingService;
import kg.attractor.projects.controlwork9.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final AuthorizedUserService authorizedUserService;
    private final TicketService ticketService;
    private final BookingMapper bookingMapper;
    @Override
    public void createBooking(Long ticketId) {
        TicketDto ticket = ticketService.getTicket(ticketId);
        Long user = authorizedUserService.getAuthorizedUserDetails().getId();
        Ticket ticket1 = new Ticket();
        ticket1.setId(ticket.getId());
        User user1 = new User();
        user1.setId(user);
        Booking booking = new Booking();
        booking.setUser(user1);
        booking.setTicket(ticket1);

        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDto> getBookings(Long userId) {
        return bookingRepository.findAllByUserId(userId)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }
}