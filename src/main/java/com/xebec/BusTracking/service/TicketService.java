package com.xebec.BusTracking.service;

import com.xebec.BusTracking.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto addTicket(TicketDto ticketDto);

    TicketDto getTicket(Long ticketId);

    List<TicketDto> getAllTickets();

    void deleteTicket(Long ticketId);

    TicketDto payTicket(TicketDto ticketDto);

    List<TicketDto> getTicketsByPassenger(Long passengerId);

    List<TicketDto> getTicketsBySchedule(Long scheduleId);

    List<TicketDto> getActiveTicketsByPassenger(Long passengerId);

    TicketDto validateTicket(String code);

    TicketDto cancelTicket(Long ticketId);
}
