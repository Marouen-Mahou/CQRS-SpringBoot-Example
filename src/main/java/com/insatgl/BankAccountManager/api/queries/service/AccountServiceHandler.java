package com.insatgl.BankAccountManager.api.queries.service;

import com.insatgl.BankAccountManager.api.commands.enums.OperationType;
import com.insatgl.BankAccountManager.api.commands.events.AccountActivatedEvent;
import com.insatgl.BankAccountManager.api.commands.events.AccountCreatedEvent;
import com.insatgl.BankAccountManager.api.commands.events.AccountCreditedEvent;
import com.insatgl.BankAccountManager.api.commands.events.AccountDebitedEvent;
import com.insatgl.BankAccountManager.api.queries.entities.Account;
import com.insatgl.BankAccountManager.api.queries.entities.Operation;
import com.insatgl.BankAccountManager.api.queries.queries.GetAccountByIdQuery;
import com.insatgl.BankAccountManager.api.queries.queries.GetAllAccountsQuery;
import com.insatgl.BankAccountManager.api.queries.repositories.AccountRepository;
import com.insatgl.BankAccountManager.api.queries.repositories.OperationRepository;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    
    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("**************************");
        log.info("AccountCreatedEvent recieved");
        
        Account account= new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setStatus(event.getStatus());
        
        
        this.accountRepository.save(account);
    }
    
    
    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("**************************");
        log.info("AccountActivatedEvent recieved");
        
        Account account = this.accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        this.accountRepository.save(account);
    }
    
    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("**************************");
        log.info("AccountCreditedEvent recieved");
        
        Account account = this.accountRepository.findById(event.getId()).get();
        
        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);
        this.operationRepository.save(operation);
        
        account.setBalance(account.getBalance() - event.getAmount());
        this.accountRepository.save(account);
    }
    
    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("**************************");
        log.info("AccountDebitedEvent recieved");
        
        Account account = this.accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() - event.getAmount());
        
        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setType(OperationType.DEBIT);
        operation.setAccount(account);
        this.operationRepository.save(operation);
        
        account.setBalance(account.getBalance() - event.getAmount());
        this.accountRepository.save(account);
    }
    
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return this.accountRepository.findAll();
    }
    
    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return this.accountRepository.findById(query.getId()).get();
    }
}
