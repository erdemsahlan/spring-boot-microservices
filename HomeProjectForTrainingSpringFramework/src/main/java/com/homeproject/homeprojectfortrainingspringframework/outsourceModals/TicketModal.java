package com.homeproject.homeprojectfortrainingspringframework.outsourceModals;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TicketModal {
    private int id;
    private String employeeIdentityNo;
    private int ticketBudget;
    private Date expiryTime;
    private boolean isActive;
}
