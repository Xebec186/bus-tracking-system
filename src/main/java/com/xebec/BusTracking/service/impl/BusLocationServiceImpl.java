package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.BusLocationDto;
import com.xebec.BusTracking.exception.ResourceNotFoundException;
import com.xebec.BusTracking.model.Bus;
import com.xebec.BusTracking.model.BusLocation;
import com.xebec.BusTracking.repository.BusLocationRepository;
import com.xebec.BusTracking.repository.BusRepository;
import com.xebec.BusTracking.service.BusLocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusLocationServiceImpl implements BusLocationService {

    private final ModelMapper modelMapper;
    private final BusLocationRepository busLocationRepository;
    private final BusRepository busRepository;

    @Override
    public BusLocationDto addBusLocation(BusLocationDto busLocationDto) {
        BusLocation busLocation = modelMapper.map(busLocationDto, BusLocation.class);

        Long busId = busLocationDto.getBusId();
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));
        busLocation.setBus(bus);

        BusLocation addedBusLocation = busLocationRepository.save(busLocation);
        return modelMapper.map(addedBusLocation, BusLocationDto.class);
    }

    @Override
    public BusLocationDto getBusLocationById(Long busLocationId) {
        BusLocation busLocation = busLocationRepository.findById(busLocationId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus location not found with id: " + busLocationId));
        return modelMapper.map(busLocation, BusLocationDto.class);
    }

    @Override
    public List<BusLocationDto> getAllBusLocations() {
        return busLocationRepository.findAll().stream()
                .map((busLocation) -> modelMapper.map(busLocation, BusLocationDto.class))
                .toList();
    }

    @Override
    public BusLocationDto updateBusLocation(Long busLocationId, BusLocationDto busLocationDto) {
        BusLocation busLocation = busLocationRepository.findById(busLocationId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus location not found with id: " + busLocationId));

        busLocation.setHeading(busLocationDto.getHeading());
        busLocation.setLongitude(busLocationDto.getLongitude());
        busLocation.setLatitude(busLocationDto.getLatitude());
        busLocation.setTimestamp(busLocationDto.getTimestamp());
        busLocation.setSpeed(busLocationDto.getSpeed());

        busLocationRepository.save(busLocation);

        return modelMapper.map(busLocation, BusLocationDto.class);
    }

    @Override
    public void deleteBusLocation(Long busLocationId) {
        BusLocation busLocation = busLocationRepository.findById(busLocationId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus location not found with id: " + busLocationId));
        busLocationRepository.delete(busLocation);
    }
}
