package com.travelq.service;

import com.travelq.dto.TravelHistoryDto;

import java.util.List;

public interface TravelHistoryService {

    TravelHistoryDto addTravelHistory(TravelHistoryDto travelhistoryDto);

    List<TravelHistoryDto> getAllTravelHistories();

    TravelHistoryDto getTravelHistoryById(Long travelHistoryId);

    TravelHistoryDto updateTravelHistory(Long travelHistoryId, TravelHistoryDto travelhistoryDto);

    void deleteTravelHistory(Long travelHistoryId);
}
