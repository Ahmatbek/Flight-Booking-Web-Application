package kg.attractor.projects.controlwork9.service;

import kg.attractor.projects.controlwork9.dto.BookingDto;

import java.util.List;

public interface BookingService {
    void createBooking(Long ticketId);
    List<BookingDto> getBookings(Long userId);
}