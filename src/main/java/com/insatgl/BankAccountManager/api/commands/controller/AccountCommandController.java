package com.insatgl.BankAccountManager.api.commands.controller;

import com.insatgl.BankAccountManager.api.commands.commands.CreateAccountCommand;
import com.insatgl.BankAccountManager.api.commands.commands.CreditAccountCommand;
import com.insatgl.BankAccountManager.api.commands.commands.DebitAccountCommand;
import com.insatgl.BankAccountManager.api.commands.dto.CreateAccountRequestDTO;
import com.insatgl.BankAccountManager.api.commands.dto.CreditAccountRequestDTO;
import com.insatgl.BankAccountManager.api.commands.dto.DebitAccountRequestDTO;
import com.insatgl.BankAccountManager.api.commands.enums.EventsType;
import com.insatgl.BankAccountManager.api.commands.model.AccountEvent;
import com.insatgl.BankAccountManager.api.commands.repository.AccountEventRepository;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/commands/account")
@AllArgsConstructor
public class AccountCommandController {
    private CommandGateway commandGateway;
    
    @Autowired
    private AccountEventRepository repo;
    
    @PostMapping(path="/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
        String _id = UUID.randomUUID().toString();
        repo.save(new AccountEvent(_id,EventsType.CREATED_EVENT,request.getInitialBalance()));
        repo.save(new AccountEvent(_id,EventsType.ACTIVATED_EVENT,request.getInitialBalance()));
         CompletableFuture<String> commandResponse =commandGateway.send(new CreateAccountCommand(
            _id,
            request.getInitialBalance()
         ));
         
        return commandResponse;
    }
    
    @PutMapping(path="/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request) {
        repo.save(new AccountEvent(request.getAccountId(),EventsType.CREDIT_EVENT,request.getAmount()));
         CompletableFuture<String> commandResponse =commandGateway.send(new CreditAccountCommand(
            request.getAccountId(),
            request.getAmount()
         ));
         
        return commandResponse;
    }
    
    @PutMapping(path="/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request) {
        repo.save(new AccountEvent(request.getAccountId(),EventsType.DEBIT_EVENT,request.getAmount()));
         CompletableFuture<String> commandResponse =commandGateway.send(new DebitAccountCommand(
            request.getAccountId(),
            request.getAmount()
         ));
         
        return commandResponse;
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity = new ResponseEntity<>(
        exception.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR
        );
        
        return entity;
    }
}
