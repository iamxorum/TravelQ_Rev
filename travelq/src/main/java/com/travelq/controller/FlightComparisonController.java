package com.travelq.controller;

import com.travelq.dto.FlightComparisonDto;
import com.travelq.service.FlightComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flightcomparisons")
public class FlightComparisonController {

    private final FlightComparisonService flightComparisonService;

    @PostMapping
    public ResponseEntity<FlightComparisonDto> create(@RequestBody FlightComparisonDto flightComparisonDto) {
        return ResponseEntity.ok(flightComparisonService.addFlightComparison(flightComparisonDto));
    }

    @GetMapping
    public ResponseEntity<List<FlightComparisonDto>> getAll() {
        return ResponseEntity.ok(flightComparisonService.getAllFlightComparisons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightComparisonDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(flightComparisonService.getFlightComparisonById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightComparisonDto> update(@PathVariable Long id, @RequestBody FlightComparisonDto flightComparisonDto) {
        return ResponseEntity.ok(flightComparisonService.updateFlightComparison(id, flightComparisonDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        flightComparisonService.deleteFlightComparison(id);
        return ResponseEntity.ok("FlightComparison with ID " + id + " was deleted.");
    }
}
