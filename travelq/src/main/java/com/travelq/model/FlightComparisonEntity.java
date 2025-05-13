package com.travelq.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flight_comparisons")
public class FlightComparisonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comparison_id")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "comparison_flights",
            joinColumns = @JoinColumn(name = "comparison_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    private List<FlightEntity> flights;

    public FlightComparisonEntity(List<FlightEntity> flights) {
        this.flights = flights;
    }
}
