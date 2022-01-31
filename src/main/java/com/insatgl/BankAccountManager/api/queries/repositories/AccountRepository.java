package com.insatgl.BankAccountManager.api.queries.repositories;


import com.insatgl.BankAccountManager.api.queries.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    
}
