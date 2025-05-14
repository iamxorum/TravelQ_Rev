package com.travelq.controller;

import com.travelq.dto.TravelOptionDto;
import com.travelq.service.TravelOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/traveloptions")
public class TravelOptionController {

    private final TravelOptionService travelOptionService;

    @PostMapping
    public ResponseEntity<TravelOptionDto> createTravelOption(@RequestBody TravelOptionDto travelOptionDto) {
        return ResponseEntity.ok(travelOptionService.addTravelOption(travelOptionDto));
    }

    @GetMapping
    public ResponseEntity<List<TravelOptionDto>> getAll() {
        return ResponseEntity.ok(travelOptionService.getAllTravelOptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelOptionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(travelOptionService.getTravelOptionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelOptionDto> update(@PathVariable Long id, @RequestBody TravelOptionDto travelOptionDto) {
        return ResponseEntity.ok(travelOptionService.updateTravelOption(id, travelOptionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        travelOptionService.deleteTravelOption(id);
        return ResponseEntity.ok("TravelOption with ID " + id + " was deleted.");
    }
}
