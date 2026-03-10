package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {
    Optional<BusLocation> findTopByBusIdOrderByTimestampDesc(Long busId);
}
