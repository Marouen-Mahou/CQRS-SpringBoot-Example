package com.insatgl.BankAccountManager.api.commands.controller;

import com.insatgl.BankAccountManager.api.commands.commands.CreateAccountCommand;
import com.insatgl.BankAccountManager.api.commands.commands.CreditAccountCommand;
import com.insatgl.BankAccountManager.api.commands.commands.DebitAccountCommand;
import com.insatgl.BankAccountManager.api.commands.dto.CreateAccountRequestDTO;
import com.insatgl.BankAccountManager.api.commands.dto.CreditAccountRequestDTO;
import com.insatgl.BankAccountManager.api.commands.dto.DebitAccountRequestDTO;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
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
    
    @PostMapping(path="/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
         CompletableFuture<String> commandResponse =commandGateway.send(new CreateAccountCommand(
            UUID.randomUUID().toString(),
            request.getInitialBalance()
         ));
         
        return commandResponse;
    }
    
    @PutMapping(path="/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request) {
         CompletableFuture<String> commandResponse =commandGateway.send(new CreditAccountCommand(
            request.getAccountId(),
            request.getAmount()
         ));
         
        return commandResponse;
    }
    
    @PutMapping(path="/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request) {
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
