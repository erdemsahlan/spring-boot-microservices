package com.springframework.ticketservice.controller;

import com.springframework.ticketservice.dto.TicketDto;
import com.springframework.ticketservice.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService _ticketService;

    public TicketController(TicketService ticketService) {
        _ticketService = ticketService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> GetAllTickets()
    {
        return _ticketService.GetAllTickets();
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> GetTicketById(@PathVariable int id)
    {
        return _ticketService.FindTicket(id);
    }
    @PostMapping("/create")
    public ResponseEntity<?> CreateTicket(@RequestBody TicketDto ticketDto)
    {
        return  _ticketService.CreateTicket(ticketDto);
    }
    @PostMapping("/update/{id}")
    public  ResponseEntity<?> UpdateTicket(@RequestBody TicketDto ticketDto,@PathVariable int id)
    {
        return _ticketService.UpdateTicket(ticketDto,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteTicket(@PathVariable int id)
    {
        return _ticketService.DeleteTicket(id);
    }

    @GetMapping("/find/tcNo/{tcNo}")
    public ResponseEntity<?> FindByIdNo(@PathVariable String tcNo)
    {
        return _ticketService.FindTicketByIdNo(tcNo);
    }



}
