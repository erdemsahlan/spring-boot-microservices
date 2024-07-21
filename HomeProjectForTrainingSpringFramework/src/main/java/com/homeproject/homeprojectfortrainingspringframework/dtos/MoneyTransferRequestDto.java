package com.homeproject.homeprojectfortrainingspringframework.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MoneyTransferRequestDto implements Serializable {
    private int senderId;
    private int receiverId;
    private int amount;
}
