package com.travelq.repository;

import com.travelq.model.TravelOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelOptionRepository extends JpaRepository<TravelOptionEntity, Long> {
    Optional<TravelOptionEntity> findByTicketId(Long ticketId);
}
