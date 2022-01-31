package com.insatgl.BankAccountManager.api.commands.commands;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommand<String>{
    @Getter private double initialBalance;
    
    public CreateAccountCommand(String id, double initialBalance) {
        super(id);
        this.initialBalance = initialBalance;
    }
    
}
