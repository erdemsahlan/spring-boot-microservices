package com.springframework.ticketservice.repository;

import com.springframework.ticketservice.modal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query("select t from Ticket t where t.employeeIdentityNo=:tcNo")
    Optional<Ticket> findTicketByIdNo(String tcNo );
}
