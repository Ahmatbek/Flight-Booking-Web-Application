package kg.attractor.projects.controlwork9.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;

    private double price;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "tickets_class_id", nullable = false)
    private TicketClass ticketClass;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean booked;
} 