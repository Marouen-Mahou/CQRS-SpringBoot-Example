package com.insatgl.BankAccountManager.api.commands.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitAccountRequestDTO {
    private String accountId;
    private double amount;
}
