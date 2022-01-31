package com.insatgl.BankAccountManager.api.commands.commands;

import lombok.Getter;

public class DebitAccountCommand extends BaseCommand<String>{
    @Getter private double amount;
    
    public DebitAccountCommand(String id, double amount) {
        super(id);
        this.amount = amount;
    }
    
}
