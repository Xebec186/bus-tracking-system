package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.TicketDto;
import com.xebec.BusTracking.exception.ResourceNotFoundException;
import com.xebec.BusTracking.model.*;
import com.xebec.BusTracking.repository.ScheduleRepository;
import com.xebec.BusTracking.repository.StopRepository;
import com.xebec.BusTracking.repository.TicketRepository;
import com.xebec.BusTracking.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final StopRepository stopRepository;
    private final ModelMapper modelMapper;

    @Override
    public TicketDto addTicket(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);

        Long passengerId = ticketDto.getPassengerId();
        User user = userRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id: " + passengerId));
        if(!user.isPassenger()) {
            throw new IllegalStateException("User must have PASSENGER role");
        }

        Long scheduleId = ticketDto.getScheduleId();
        Schedule schedule = scheduleRepository.findById(scheduleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with given id: " + scheduleId));

        Long originStopId = ticketDto.getOriginStopId();
        Long destinationStopId = ticketDto.getDestinationStopId();
        Stop originStop = stopRepository.findById(originStopId)
                        .orElseThrow(() -> new ResourceNotFoundException("Stop not found with given id: " + originStopId));
        Stop destinatiionStop = stopRepository.findById(destinationStopId)
                .orElseThrow(() -> new ResourceNotFoundException("Stop not found with given id: " + destinationStopId));

        ticket.setPassenger(user);
        ticket.setSchedule(schedule);
        ticket.setOriginStop(originStop);
        ticket.setDestinationStop(destinatiionStop);

        Ticket addedTicket = ticketRepository.save(ticket);

        return modelMapper.map(addedTicket, TicketDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketDto getTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with given id: " + ticketId));
        return modelMapper.map(ticket, TicketDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .toList();
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

        ticket.markAsPaid(ticketDto.getPaymentMethod(), ticketDto.getPaymentReference());

        Ticket paidTicket = ticketRepository.save(ticket);
        return modelMapper.map(paidTicket, TicketDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getTicketsByPassenger(Long passengerId) {
        return ticketRepository.findByPassengerId(passengerId).stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getTicketsBySchedule(Long scheduleId) {
        return ticketRepository.findByScheduleId(scheduleId).stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> getActiveTicketsByPassenger(Long passengerId) {
        return ticketRepository.findByPassengerId(passengerId).stream()
                .filter(ticket -> ticket.getStatus().equals(TicketStatus.PAID) || ticket.getStatus().equals(TicketStatus.PENDING))
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .toList();
    }

    @Override
    public TicketDto validateTicket(String code) {
        Ticket ticket = ticketRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with given code: " + code));

        ticket.validate();

        Ticket validatedTicket = ticketRepository.save(ticket);
        return modelMapper.map(validatedTicket, TicketDto.class);
    }

    @Override
    public TicketDto cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with given id: " + ticketId));
        ticket.cancel();
        Ticket canclledTicket = ticketRepository.save(ticket);
        return modelMapper.map(canclledTicket, TicketDto.class);
    }
}
