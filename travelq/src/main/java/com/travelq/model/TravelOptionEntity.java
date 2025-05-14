package com.travelq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "travel_options")
public class TravelOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "check_in", nullable = false)
    private boolean checkIn;

    @Column(name = "seat_selection", nullable = false)
    private TravelOptionSeatSelectionEnum seatSelection;

    @Column(name = "extra_baggage")
    private TravelOptionExtraBaggageEnum extraBaggage;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false, unique = true)
    private TicketEntity ticket;
}
