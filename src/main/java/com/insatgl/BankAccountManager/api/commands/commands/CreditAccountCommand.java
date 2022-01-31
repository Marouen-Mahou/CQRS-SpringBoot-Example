package com.insatgl.BankAccountManager.api.commands.commands;

import lombok.Getter;

public class CreditAccountCommand extends BaseCommand<String>{
    @Getter private double amount;
    
    public CreditAccountCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }
    
}
