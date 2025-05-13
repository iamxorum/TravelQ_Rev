package com.travelq.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "travel_history")
public class TravelHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "flights_count", nullable = false)
    private int flightsCount;

    @Column(name = "total_spent", nullable = false)
    private double totalSpent;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    public TravelHistoryEntity(int flightsCount, double totalSpent, UserEntity user) {
        this.flightsCount = flightsCount;
        this.totalSpent = totalSpent;
        this.user = user;
    }
}
