package com.insatgl.BankAccountManager.api.commands.model;

import com.insatgl.BankAccountManager.api.commands.enums.EventsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "accountevent")
public class AccountEvent {
    private String accountId;
    private EventsType event;
    private double amount;

    public AccountEvent(String accountId, EventsType event, double amount) {
        this.accountId = accountId;
        this.event = event;
        this.amount = amount;
    }
    

}
