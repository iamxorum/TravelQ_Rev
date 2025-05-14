package com.travelq.repository;

import com.travelq.model.FlightComparisonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightComparisonRepository extends JpaRepository<FlightComparisonEntity, Long> {
}
