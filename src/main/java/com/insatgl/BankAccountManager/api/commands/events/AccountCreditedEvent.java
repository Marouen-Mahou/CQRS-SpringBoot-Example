package com.insatgl.BankAccountManager.api.commands.events;

import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String> {
    @Getter private double amount;

    public AccountCreditedEvent(String id, double amount) {
        super(id);
        this.amount = amount;
    }
    
}
