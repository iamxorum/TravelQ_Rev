package com.travelq.controller;

import com.travelq.dto.TravelHistoryDto;
import com.travelq.service.TravelHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/travelhistories")
public class TravelHistoryController {

    private final TravelHistoryService travelHistoryService;

    @PostMapping
    public ResponseEntity<TravelHistoryDto> create(@RequestBody TravelHistoryDto travelHistoryDto) {
        return ResponseEntity.ok(travelHistoryService.addTravelHistory(travelHistoryDto));
    }

    @GetMapping
    public ResponseEntity<List<TravelHistoryDto>> getAll() {
        return ResponseEntity.ok(travelHistoryService.getAllTravelHistories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelHistoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(travelHistoryService.getTravelHistoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelHistoryDto> update(@PathVariable Long id, @RequestBody TravelHistoryDto travelHistoryDto) {
        return ResponseEntity.ok(travelHistoryService.updateTravelHistory(id, travelHistoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        travelHistoryService.deleteTravelHistory(id);
        return ResponseEntity.ok("TravelHistory with ID " + id + " was deleted.");
    }
}
