package com.springframework.ticketservice.service;

import com.springframework.ticketservice.dto.TicketDto;
import com.springframework.ticketservice.modal.Ticket;
import com.springframework.ticketservice.repository.IGenericRepository;
import com.springframework.ticketservice.utilities.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    IGenericRepository<Ticket> _ticket;

    @Autowired
    public void setDao(IGenericRepository<Ticket> jpaToSet) {
        _ticket = jpaToSet;
        _ticket.setClas(Ticket.class);
    }

    public ResponseEntity<?> CreateTicket(TicketDto ticketDto)
    {
        Ticket ticket= Mapper.map(ticketDto,Ticket.class);
        _ticket.create(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDto);
    }
    public ResponseEntity<?> GetAllTickets()
    {
        List<Ticket> ticket = _ticket.findAll();
        List<TicketDto> ticketDtos = Mapper.mapAll(ticket,TicketDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(ticketDtos);
    }
    public ResponseEntity<?> FindTicket(int id)
    {
        Ticket ticket = _ticket.findOne(id);
        TicketDto ticketDto = Mapper.map(ticket,TicketDto.class);
        return  ResponseEntity.status(HttpStatus.OK).body(ticketDto);
    }
    public ResponseEntity<?> UpdateTicket(TicketDto ticketDto)
    {
        Ticket ticket = _ticket.findOne(ticketDto.getId());
        ticket.setEmployeeIdentityNo(ticketDto.getEmployeeIdentityNo());
        ticket.setExpiryTime(ticketDto.getExpiryTime());
        ticket.setTicketBudget(ticketDto.getTicketBudget());
        ticket.setActive(ticketDto.isActive());
        _ticket.update(ticket);
        return ResponseEntity.status(HttpStatus.OK).body(ticketDto);
    }
    public ResponseEntity<?> DeleteTicket(int id)
    {
        Ticket ticket = _ticket.findOne(id);
        ticket.setActive(false);
        _ticket.update(ticket);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
