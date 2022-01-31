package com.insatgl.BankAccountManager.api.commands.events;

import lombok.Getter;

public class AccountDebitedEvent extends BaseEvent<String> {
    @Getter private double amount;

    public AccountDebitedEvent(String id, double amount) {
        super(id);
        this.amount = amount;
    }
    
}
