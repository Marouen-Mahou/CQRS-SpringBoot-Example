package com.insatgl.BankAccountManager.api.queries.controllers;

import com.insatgl.BankAccountManager.api.queries.entities.Account;
import com.insatgl.BankAccountManager.api.queries.queries.GetAccountByIdQuery;
import com.insatgl.BankAccountManager.api.queries.queries.GetAllAccountsQuery;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query/accounts")
@AllArgsConstructor
@Slf4j
public class AccountQueryController {
    private QueryGateway queryGateway;
    
    @GetMapping("/accounts")
    public List<Account> accountList() {
        List<Account> response = this.queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return response;
    }
    
    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable String id){
        Account response = queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
        return response;
    }
}
