package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.Ticket;
import com.xebec.BusTracking.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByCode(String code);
    List<Ticket> findByPassengerId(Long passengerId);
    List<Ticket> findByScheduleId(Long scheduleId);
    List<Ticket> findByPassengerIdAndStatusIn(Long passengerId, List<TicketStatus> statuses);
}
