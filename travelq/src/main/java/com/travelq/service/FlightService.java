package com.travelq.service;

import com.travelq.dto.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto addFlight(FlightDto flightDto);

    List<FlightDto> getAllFlights();

    FlightDto getFlightById(Long flightId);

    FlightDto updateFlight(Long flightId, FlightDto flightDto);

    void deleteFlight(Long flightId);
}
