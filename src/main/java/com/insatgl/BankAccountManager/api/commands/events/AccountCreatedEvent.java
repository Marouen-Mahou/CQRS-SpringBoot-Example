
package com.insatgl.BankAccountManager.api.commands.events;

import com.insatgl.BankAccountManager.api.commands.enums.AccountStatus;
import lombok.Getter;


public class AccountCreatedEvent extends BaseEvent<String> {
    @Getter private double initialBalance;
    @Getter private AccountStatus status;

    public AccountCreatedEvent(String id, double initialBalance, AccountStatus status) {
        super(id);
        this.initialBalance = initialBalance;
        this.status = status;
    }
    
}
