package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.TicketDto;
import com.xebec.BusTracking.exception.ResourceNotFoundException;
import com.xebec.BusTracking.model.Ticket;
import com.xebec.BusTracking.model.TicketStatus;
import com.xebec.BusTracking.repository.TicketRepository;
import com.xebec.BusTracking.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getTicket(Long ticketId) {
        return null;
    }

    @Override
    public List<TicketDto> getAllTickets() {
        return List.of();
    }

    @Override
    public TicketDto updateTicket(Long ticketId, TicketDto ticketDto) {
        return null;
    }

    @Override
    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with given id: " + ticketId));
        ticketRepository.delete(ticket);
    }

    @Override
    public TicketDto payTicket(TicketDto ticketDto) {
        Long ticketId = ticketDto.getId();
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with given id: " + ticketId));

        ticket.setStatus(TicketStatus.PAID);
        ticket.setPaymentMethod(ticketDto.getPaymentMethod());
        ticket.setPaymentReference(ticketDto.getPaymentReference());

        Ticket paidTicket = ticketRepository.save(ticket);

        return modelMapper.map(paidTicket, TicketDto.class);
    }

    @Override
    public List<TicketDto> getTicketsByPassenger(Long passengerId) {
        return List.of();
    }

    @Override
    public List<TicketDto> getTicketsBySchedule(Long scheduleId) {
        return List.of();
    }

    @Override
    public List<TicketDto> getActiveTicketsByPassenger(Long passengerId) {
        return List.of();
    }

    @Override
    public TicketDto validateTicket(String code) {
        return null;
    }

    @Override
    public TicketDto cancelTicket(Long ticketId) {
        return null;
    }
}
