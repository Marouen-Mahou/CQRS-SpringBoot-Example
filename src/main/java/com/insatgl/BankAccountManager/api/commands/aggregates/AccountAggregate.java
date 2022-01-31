package com.insatgl.BankAccountManager.api.commands.aggregates;

import com.insatgl.BankAccountManager.api.commands.commands.CreateAccountCommand;
import com.insatgl.BankAccountManager.api.commands.commands.CreditAccountCommand;
import com.insatgl.BankAccountManager.api.commands.commands.DebitAccountCommand;
import com.insatgl.BankAccountManager.api.commands.enums.AccountStatus;
import com.insatgl.BankAccountManager.api.commands.events.AccountActivatedEvent;
import com.insatgl.BankAccountManager.api.commands.events.AccountCreatedEvent;
import com.insatgl.BankAccountManager.api.commands.events.AccountCreditedEvent;
import com.insatgl.BankAccountManager.api.commands.events.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


@Aggregate
public class AccountAggregate {
    
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private AccountStatus status;
    
    public AccountAggregate(){
        //Required by AXON
    }
   
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        if(createAccountCommand.getInitialBalance()<0) throw new RuntimeException("Balance is negatif");
        
        AggregateLifecycle.apply(new AccountCreatedEvent(
         createAccountCommand.getId(),
         createAccountCommand.getInitialBalance(),
         status.CREATED 
        ));
    }
    
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.status = AccountStatus.CREATED;
        
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.accountId = event.getId();
        this.status = event.getStatus();
    }
    
    @CommandHandler
    public void handle(CreditAccountCommand command){
        if(command.getAmount()<0) throw new RuntimeException("Amount should be postive");
        
        AggregateLifecycle.apply(new AccountCreditedEvent(
           command.getId(),
           command.getAmount()
        ));
    }
    
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+= event.getAmount();
    }
    
        @CommandHandler
    public void handle(DebitAccountCommand command){
        if(command.getAmount()<0) throw new RuntimeException("Amount should be postive");
        if(this.balance<command.getAmount()) throw new RuntimeException("No balance available");
        AggregateLifecycle.apply(new AccountCreditedEvent(
           command.getId(),
           command.getAmount()
        ));
    }
    
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-= event.getAmount();
    }
}
