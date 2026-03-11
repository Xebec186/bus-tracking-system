package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByPassengerId(Long passengerId);
    List<Ticket> findByScheduleId(Long scheduleId);
}
