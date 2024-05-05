package com.springframework.ticketservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TicketDto {
    private String employeeIdentityNo;
    private int ticketBudget;
    private Date expiryTime;
    private boolean isActive;
}
