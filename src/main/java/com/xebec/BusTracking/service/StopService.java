package com.xebec.BusTracking.service;

import com.xebec.BusTracking.dto.StopDto;

import java.util.List;

public interface StopService {
    StopDto addStop(StopDto stopDto);

    StopDto getStopById(Long stopId);

    List<StopDto> getAllStops();

    StopDto updateStop(Long stopId, StopDto stopDto);

    void deleteStop(Long stopId);
}
