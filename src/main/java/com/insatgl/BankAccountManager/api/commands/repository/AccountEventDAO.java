/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.insatgl.BankAccountManager.api.commands.repository;

import com.insatgl.BankAccountManager.api.commands.model.AccountEvent;
import com.insatgl.BankAccountManager.api.queries.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountEventDAO {
    @Autowired
    private AccountEventRepository repo;
    
    public AccountEventDAO(){
        
    }
    
    public void add(AccountEvent account){
        System.out.print(repo);
        repo.save(account);
    }
    
}
