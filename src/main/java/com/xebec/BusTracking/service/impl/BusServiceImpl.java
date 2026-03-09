package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.BusDto;
import com.xebec.BusTracking.exception.ResourceNotFoundException;
import com.xebec.BusTracking.model.Bus;
import com.xebec.BusTracking.model.User;
import com.xebec.BusTracking.repository.BusRepository;
import com.xebec.BusTracking.repository.UserRepository;
import com.xebec.BusTracking.service.BusService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final ModelMapper modelMapper;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    @Override
    public BusDto addBus(BusDto busDto) {
        Bus bus = modelMapper.map(busDto, Bus.class);
        Bus addedBus = busRepository.save(bus);
        return modelMapper.map(addedBus, BusDto.class);
    }

    @Override
    public BusDto getBusById(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));
        return modelMapper.map(bus, BusDto.class);
    }

    @Override
    public List<BusDto> getAllBuses() {
        return busRepository.findAll().stream()
                .map((bus -> modelMapper.map(bus, BusDto.class)))
                .toList();
    }

    @Override
    public BusDto updateBus(Long busId, BusDto busDto) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));

        bus.setModel(bus.getModel());
        bus.setMake(busDto.getMake());
        bus.setCapacity(busDto.getCapacity());
        bus.setRegistrationNumber(busDto.getRegistrationNumber());

        return modelMapper.map(bus, BusDto.class);
    }

    @Override
    public void deleteBus(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));
        busRepository.delete(bus);
    }

    @Override
    public void assignDriver(Long busId, Long driverId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));

        User driver = userRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id: " + driverId));

        bus.assignDriver(driver);

        busRepository.save(bus);
    }

    @Override
    public void removeDriver(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));

        bus.removeDriver();

        busRepository.save(bus);
    }

    @Override
    public void setToMaintenanceMode(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with given id: " + busId));

        bus.setMaintenance();

        busRepository.save(bus);
    }
}
