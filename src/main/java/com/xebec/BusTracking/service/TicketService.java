package com.xebec.BusTracking.service;

import com.xebec.BusTracking.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto addTicket(TicketDto ticketDto);

    TicketDto getTicket(Long ticketId);

    List<TicketDto> getAllTickets();

    TicketDto updateTicket(Long ticketId, TicketDto ticketDto);

    void deleteTicket(Long ticketId);
}
