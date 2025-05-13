package com.travelq.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "stopovers", nullable = false)
    private int stopovers;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<TicketEntity> tickets;

    @ManyToMany(mappedBy = "flights", fetch = FetchType.LAZY)
    private List<FlightComparisonEntity> comparisons;

    public FlightEntity(String origin, String destination, LocalDateTime departureTime,
                        LocalDateTime arrivalTime, double price, int stopovers, List<TicketEntity> tickets,
                        List<FlightComparisonEntity> comparisons) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.stopovers = stopovers;
        this.tickets = tickets;
        this.comparisons = comparisons;
    }

}
