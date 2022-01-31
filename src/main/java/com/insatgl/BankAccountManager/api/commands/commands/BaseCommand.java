package com.insatgl.BankAccountManager.api.commands.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter private T id;
    
    public BaseCommand(T id){
        this.id = id;
    }
    
}