package com.travelq.service;

import com.travelq.dto.FlightDto;
import com.travelq.exception.FlightNotFoundException;
import com.travelq.model.FlightEntity;
import com.travelq.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Override
    public FlightDto addFlight(FlightDto flightDto) {
        FlightEntity flightEntity = modelMapper.map(flightDto, FlightEntity.class);
        FlightEntity saved = flightRepository.save(flightEntity);
        return modelMapper.map(saved, FlightDto.class);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<FlightEntity> flightList = flightRepository.findAll();
        return flightList.stream()
                .map(flightEntity -> modelMapper.map(flightEntity, FlightDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        FlightEntity flightEntity = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(flightId));
        return modelMapper.map(flightEntity, FlightDto.class);
    }

    @Override
    public FlightDto updateFlight(Long flightId, FlightDto flightDto) {
        FlightEntity existingFlight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(flightId));
        modelMapper.map(flightDto, existingFlight);
        FlightEntity updatedFlight = flightRepository.save(existingFlight);
        return modelMapper.map(updatedFlight, FlightDto.class);
    }

    @Override
    public void deleteFlight(Long flightId) {
        if (!flightRepository.existsById(flightId)) {
            throw new FlightNotFoundException(flightId);
        }
        flightRepository.deleteById(flightId);
    }
}
