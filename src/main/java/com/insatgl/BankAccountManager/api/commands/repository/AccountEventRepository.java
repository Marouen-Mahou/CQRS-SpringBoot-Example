/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.insatgl.BankAccountManager.api.commands.repository;

import com.insatgl.BankAccountManager.api.commands.model.AccountEvent;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountEventRepository extends MongoRepository<AccountEvent, String>{
    
}
